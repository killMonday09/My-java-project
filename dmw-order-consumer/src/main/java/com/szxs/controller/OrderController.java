package com.szxs.controller;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.dto.OrderDto;
import com.szxs.dto.OrderListDto;
import com.szxs.entity.DmOrder;
import com.szxs.service.OrderService;
import com.szxs.util.DmwConstant;
import com.szxs.util.RedisUtil;
import com.szxs.util.VoUtil;
import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.vo.RespDataVo;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/createOrder")
    public RespDataVo createOrder(@RequestBody OrderDto orderDto,@RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        orderDto.setUserId(Integer.parseInt(id));
        try {
            RespDataVo success = VoUtil.getSuccess(orderService.createOrder(orderDto));
            success.setErrorCode("0000");
            return success;

        }catch (Exception e){
            log.info(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryOrderByOrderNo")
    public RespDataVo queryOrderByOrderNo(@RequestBody OrderByOrderNoDto orderByOrderNoDto){
        try {
            RespDataVo success = VoUtil.getSuccess(orderService.OrderByOrderNo(orderByOrderNoDto));
            success.setErrorCode("0000");
            return success;

        }catch (Exception e){
            log.info(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @RequestMapping("/queryOrderList")
    public RespDataVo queryOrderList(@RequestBody OrderListDto orderListDto,@RequestHeader String token){
        Map<String, String> glist = redisUtil.glist2(token);
        String id = glist.get("id");
        orderListDto.setUserId(Integer.parseInt(id));

        try {
            RespDataVo success = VoUtil.getSuccess(orderService.queryOrderList(orderListDto));
            success.setErrorCode("0000");
            return success;

        }catch (Exception e){
            log.info(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }
    @PostMapping("/queryOrderState")
    public RespDataVo queryOrderState(@RequestBody OrderByOrderNoDto orderByOrderNoDto){
        try {
            RespDataVo success = VoUtil.getSuccess(orderService.queryOrderState(orderByOrderNoDto));
            success.setErrorCode("0000");
            return success;

        }catch (Exception e){
            log.info(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

}
