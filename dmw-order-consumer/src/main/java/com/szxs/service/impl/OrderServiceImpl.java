package com.szxs.service.impl;
import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.dto.OrderDto;
import com.szxs.dto.OrderListDto;
import com.szxs.entity.*;
import com.szxs.feign.ItemTypeClient;
import com.szxs.feign.OrderClient;
import com.szxs.feign.SchedulerClient;
import com.szxs.feign.UserLoginClient;
import com.szxs.service.OrderService;
import com.szxs.util.*;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderStateVo;
import com.szxs.vo.OrderVo;
import com.szxs.vo.UserVoById;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderListVo;
import com.szxs.vo.OrderVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private SchedulerClient schedulerClient;
    @Autowired
    private ItemTypeClient itemTypeClient;
    @Autowired
    private UserLoginClient userLoginClient;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public OrderVo createOrder(OrderDto orderDto) {
        DmOrder dmOrder = new DmOrder();
        String orderNo = OrderUtil.Order()+OrderUtil.MathOrder(6);
        OrderVo orderVo = new OrderVo();

        if (EmptyUtil.isNotEmpty(orderDto)){

            DmItem item = itemTypeClient.queryItemNameById(orderDto.getItemId());
            if (EmptyUtil.isEmpty(item)){
                VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,"此商品信息不存在！");
            }

            //拆分座位
            String[] seatArray = orderDto.getSeatPositions().split(",");

            try {
                //先把当前座位对应的剧场锁定，避免同时操作
                while (!redisUtil.lock(String.valueOf(orderDto.getSchedulerId()))){
                    //当前正被别人使用
                    Thread.sleep(3);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isLock = false;
            //查看当前座位是否被占用  如果已经被占用则直接返回下单失败
            for (int i = 0; i < seatArray.length; i++) {
                if (EmptyUtil.isNotEmpty(redisUtil.get(orderDto.getSchedulerId() + ":" + seatArray[i]))){
                    //当前座位已经存到redis中，代表已经被占用
                    isLock = true;
                    break;
                }
            }
            if (isLock){
                //座位已经被占用
                redisUtil.unlock(String.valueOf(orderDto.getSchedulerId()));
                throw  new RuntimeException("此座位已被选购！");
            }


            //总价格
            double totalAmount = 0;
            //价格座位集合
            Double[] doublePrice = new Double[seatArray.length];
            DmSchedulerSeat dmSchedulerSeat = null;
            for (int i = 0; i < seatArray.length; i++) {
                String[] seats = seatArray[i].split("_");
                //查询每一个座位信息
                dmSchedulerSeat = schedulerClient.querySchedulerSeatByOrder(orderDto.getSchedulerId(),
                        Integer.parseInt(seats[0]),
                        Integer.parseInt(seats[1]));
                //更新座位状态为锁定状态
                dmSchedulerSeat.setStatus(2);
                //更新下单用户
                dmSchedulerSeat.setUserId(orderDto.getUserId());
                //更新订单编号
                dmSchedulerSeat.setOrderNo(orderNo);
                //更新数据库
                schedulerClient.modifySchedulerSeat(dmSchedulerSeat);
                //设置总价格
                DmSchedulerSeatPrice dmSchedulerSeatPrice = schedulerClient.querySchedulerPriceBySchedulerIdAndArea(dmSchedulerSeat.getScheduleId(),
                dmSchedulerSeat.getAreaLevel());
                totalAmount += dmSchedulerSeatPrice.getPrice();
                doublePrice[i] = dmSchedulerSeatPrice.getPrice();

            }


            //设置订单号
            dmOrder.setOrderNo(orderNo);
            //copy到pojo
            BeanUtils.copyProperties(orderDto,dmOrder);
            //调用查询itemname接口
            DmItem dmItem = itemTypeClient.queryItemNameById(orderDto.getItemId());
            //设置name
            dmOrder.setItemName(dmItem.getItemName());
            //设置支付状态
            dmOrder.setOrderType(AlxConstant.Order.NO_PAY);
            //设置座位数量
            dmOrder.setTotalCount(seatArray.length);
            //设置支付端
            dmOrder.setSourceType(AlxConstant.Common.ZERO);
            //是否需要发票
            if (orderDto.getIsNeedInsurance() == AlxConstant.Common.ONE){
                totalAmount += AlxConstant.Order.NEEDUNSYRANCE_MOENY;
            }
            //设置总价格
            dmOrder.setTotalAmount(totalAmount);
            //保险价格
            dmOrder.setInsuranceAmount(AlxConstant.Order.NEEDUNSYRANCE_MOENY);
            //创建时间
            dmOrder.setCreatedTime(new Date());
            //更新插入数据
            int order = 0;
            try {
                //保存订单 生成订单号id
                order = orderClient.createOrder(dmOrder);
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
            //更新相关关联用户
            String[] linkIds = orderDto.getLinkIds().split(",");
            for (int i = 0; i < linkIds.length; i++) {
                DmLinkUser linkUser = userLoginClient.getLinkUser(Integer.parseInt(linkIds[i]));
                DmOrderLinkUser dmOrderLinkUser = new DmOrderLinkUser();
                dmOrderLinkUser.setOrderId(order);
                dmOrderLinkUser.setLinkUserId(linkUser.getId());
                dmOrderLinkUser.setLinkUserName(linkUser.getName());
                //拆分作为信息 x排 y坐
                dmOrderLinkUser.setX(Integer.parseInt(seatArray[i].split("_")[0]));
                dmOrderLinkUser.setY(Integer.parseInt(seatArray[i].split("_")[1]));
                //座位价格
                dmOrderLinkUser.setPrice(doublePrice[i]);
                dmOrderLinkUser.setCreatedTime(new Date().toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime());
                //把查询数据保存到订单常用联系人
                orderClient.addOrderLinkUser(dmOrderLinkUser);
            }

            //返回前端订单号
            orderVo.setOrderNo(orderNo);
            //如果下单成功 将座位锁定
            setSeatLock(orderDto,seatArray);

            /**
             * 查询库存状态 减库存
             */
            //dmSchedulerSeat.getAreaLevel(); dmSchedulerSeat.scheduleId条件
            Integer areaLevel = dmSchedulerSeat.getAreaLevel();
            Integer scheduleId = dmSchedulerSeat.getScheduleId();
            Integer length = seatArray.length;
            orderClient.updateSeatNum(areaLevel,scheduleId,length);
            //把座位等级 排期id 座位数量存到redis
            redisUtil.set(orderNo+":areaLevel",String.valueOf(areaLevel),10800);
            redisUtil.set(orderNo+":scheduleId",String.valueOf(scheduleId),10800);
            redisUtil.set(orderNo+":seatNum",String.valueOf(length),10800);

            //将座位信息存到redis 给下面查询订单信息用 键：orderVo.getOrderNo()+"seat" 值：orderDto.getSeatPositions()
            redisUtil.set(orderVo.getOrderNo()+"seat",orderDto.getSeatPositions(),3000);
        }
        return orderVo;
    }

    @Override
    public OrderByOrderNoVo OrderByOrderNo(OrderByOrderNoDto orderByOrderNoDto) {
        OrderByOrderNoVo byOrderNoVo = null;

        if (EmptyUtil.isNotEmpty(orderByOrderNoDto)){
            //redis拿座位信息
            String seat = redisUtil.get(orderByOrderNoDto.getOrderNo() + "seat");
            //根据前段发来的订单号查询订单信息
            byOrderNoVo = orderClient.queryOrderByOrderNo(orderByOrderNoDto);
            //设置座位
            byOrderNoVo.setSeatName(seat);
        }
        return byOrderNoVo;
    }


    /**
     * 将座位锁定保存到redis中
     * @param orderDto
     * @param seatArray
     */
    private void setSeatLock(OrderDto orderDto, String[] seatArray) {
        redisUtil.unlock(String.valueOf(orderDto.getSchedulerId()));
        for (int i = 0; i < seatArray.length; i++) {
            redisUtil.setNX(orderDto.getSchedulerId()+":"+seatArray[i],"lock");
        }
    }



    @Override
    public List<OrderListVo> queryOrderList(OrderListDto orderListDto) {
        String keyword = orderListDto.getKeyword();
        if (orderListDto.getOrderType() == 3){
            orderListDto.setOrderType(-3);
        }
//        redisUtil.set("keyword",keyword);
//        String kw = redisUtil.get("keyword");
//        if (orderListDto.getKeyword() == kw){
//            orderListDto.setKeyword(null);
//        }
        List<OrderListVo> orderListVos = orderClient.queryOrderList(orderListDto);
        for (OrderListVo orderListVo : orderListVos ){
            orderListVo.setUnitPrice(orderListVo.getTotalAmount()/orderListVo.getNum());
        }
        return orderListVos;
    }

    @Override
    public OrderStateVo queryOrderState(OrderByOrderNoDto orderByOrderNoDto) {
        OrderStateVo orderStateVo = null;
        if (EmptyUtil.isNotEmpty(orderByOrderNoDto)){
            orderStateVo = orderClient.queryOrderState(orderByOrderNoDto);
        }
        return orderStateVo;
    }

}
