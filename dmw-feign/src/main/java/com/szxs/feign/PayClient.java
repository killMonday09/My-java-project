package com.szxs.feign;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.vo.PayOrderNoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("dmw-pay-provider")
public interface PayClient {
    @PostMapping("/savePay")
    boolean savePay(@RequestBody DmTrade dmTrade);

    @PostMapping("/pay")
    PayOrderNoVo payOrderNo(@RequestParam("orderNo") String orderNo);

    @PostMapping("/updateOrderType")
    boolean updateOrderType(@RequestBody DmOrder dmOrder);

    @PostMapping("/queryOrder")
    DmOrder queryOrder(@RequestParam("orderNo") String orderNo);

    @PostMapping("/updateSeatStatus")
    boolean updateSeatStatus(@RequestBody DmSchedulerSeat dmSchedulerSeat);
}
