package com.szxs.controller;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.mapper.PayMapper;
import com.szxs.vo.OrderStateVo;
import com.szxs.vo.PayOrderNoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.util.Date;

@RestController
public class RestPayController {
    @Autowired
    private PayMapper payMapper;

    @PostMapping("/savePay")
    public boolean savePay(@RequestBody DmTrade dmTrade){
        dmTrade.setCreatedTime(new Date());
        return payMapper.savePay(dmTrade) > 0;
    }

    @PostMapping("/pay")
    public PayOrderNoVo payOrderNo(@RequestParam("orderNo") String orderNo){
        PayOrderNoVo payOrderNoVo = payMapper.queryPayOrderNo(orderNo);
        return payOrderNoVo;
    }

    @PostMapping("/updateOrderType")
    public boolean updateOrderType(@RequestBody DmOrder dmOrder){
        return payMapper.updateOrderType(dmOrder) > 0;
    }

    @PostMapping("/queryOrder")
    public DmOrder queryOrder(@RequestParam("orderNo") String orderNo){
        return payMapper.queryOrder(orderNo);
    }

    @PostMapping("/updateSeatStatus")
    public boolean updateSeatStatus(@RequestBody DmSchedulerSeat dmSchedulerSeat){
        return payMapper.updateSeatStatus(dmSchedulerSeat) > 0;
    }
}
