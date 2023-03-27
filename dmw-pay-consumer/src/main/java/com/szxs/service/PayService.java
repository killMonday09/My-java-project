package com.szxs.service;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.PayOrderNoVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface PayService {

    boolean syncVerifyResult(Map<String,String[]> requesParms) throws Exception;

    boolean saveTrade(DmTrade dmTrade);

    PayOrderNoVo queryPayOrderNo(String orderNo);

    boolean updateOrderType(DmOrder dmOrder);

    DmOrder queryOrder(@RequestParam("orderNo") String orderNo);

    boolean updateSeatStatus(DmSchedulerSeat dmSchedulerSeat);
}
