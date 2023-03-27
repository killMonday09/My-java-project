package com.szxs.controller;

import com.rabbitmq.client.Channel;
import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.dto.OrderListDto;
import com.szxs.entity.*;
import com.szxs.exception.DmwException;
import com.szxs.mapper.OrderMapper;
import com.szxs.util.RedisUtil;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderListVo;
import com.szxs.vo.OrderStateVo;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class RestOrderController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisUtil redisUtil;

    private String orderExchange = "mayikt.order.exchange"; //订单交换机

    private String orderRoutingKey = "mayikt.order.routingKey"; //订单路由key

    @PostMapping("/createOrder")
    public int createOrder(@RequestBody DmOrder dmOrder){
        dmOrder.setCreatedTime(new Date());
        String orderNo = dmOrder.getOrderNo();
        int order = orderMapper.saveOrder(dmOrder);
        if (order > 0){
            //rabbit投递消息
            rabbitTemplate.convertAndSend(orderExchange,orderRoutingKey,orderNo,messagePostProcessor());
        }else {
            throw new DmwException("新增订单出错了");
        }
        return dmOrder.getId();
    }


    @RequestMapping("/queryOrderList")
    public List<OrderListVo> queryOrderList(@RequestBody OrderListDto orderListDto){
        return orderMapper.queryOrderList(orderListDto);
    }

    @PostMapping("/modifyOrder")
    public int modifyOrder(@RequestBody DmOrder dmOrder){
        dmOrder.setCreatedTime(new Date());
        orderMapper.updateOrder(dmOrder);
        return dmOrder.getId();
    }

    @PostMapping("/queryOrderByOrderNo")
    public OrderByOrderNoVo queryOrderByOrderNo(@RequestBody OrderByOrderNoDto orderByOrderNoDto){
        return orderMapper.queryOrderByOrderNo(orderByOrderNoDto);
    }

    @PostMapping("/addOrderLinkUser")
    public boolean addOrderLinkUser(@RequestBody DmOrderLinkUser dmOrderLinkUser){
        return orderMapper.addOrderLinkUser(dmOrderLinkUser) > 0;
    }

    @PostMapping("/queryOrderState")
    public OrderStateVo queryOrderState(@RequestBody OrderByOrderNoDto orderByOrderNoDto){
        return orderMapper.queryOrderState(orderByOrderNoDto);
    }

    @RequestMapping("/updateSeatNum")
    public boolean updateSeatNum(@RequestParam("areaLevel") Integer areaLevel,
                                 @RequestParam("scheduleId") Integer scheduleId,
                                 @RequestParam("seatNum") Integer seatNum){
        return orderMapper.updateSeatNum(areaLevel,scheduleId,seatNum) > 0;
    }


    /**
     * 监听死信队列 如果超时未支付 修改订单状态
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "mayikt.dlx.queue") //监听死信队列
    public void listener(Message message, Channel channel) throws IOException {

        try {
            String ss = new String(message.getBody());
            System.out.println("监听到死信队列消息 订单号为： "+ss);

            DmOrder dmOrder = orderMapper.queryOrderByOrder(ss);
            if (dmOrder.getOrderType() == 0){

                //修改支付状态
                orderMapper.updateOrderType(ss,-1);

                //redis取信息
                String areaLevel = redisUtil.get(ss + ":areaLevel");
                String scheduleId = redisUtil.get(ss + ":scheduleId");
                String seatNum = redisUtil.get(ss + ":seatNum");

                //返回库存
                orderMapper.updateAddSeatNum(Integer.parseInt(areaLevel),
                        Integer.parseInt(scheduleId),
                        Integer.parseInt(seatNum));

                //修改座位状态
                orderMapper.updateSeatStatus(ss,Integer.parseInt(scheduleId));

                //redisUtil.delete(scheduleId);
                String s = redisUtil.get(ss + "seat");
                String[] strings = s.split(",");
                for (int i = 0; i < strings.length; i++) {
                    redisUtil.delete(scheduleId+":"+strings[i]);
                }

            }

            //回滚订单座位作态

            //手动确认
            //false单条确认
            //true批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);


        }catch (Exception e){
            //拒签
            System.out.println(e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }

    }


    //处理待发送消息
    private MessagePostProcessor messagePostProcessor(){
        return new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置有效期30分钟
                //message.getMessageProperties().setExpiration("1800000");
                message.getMessageProperties().setExpiration("120000");
                return message;
            }
        };
    }
}
