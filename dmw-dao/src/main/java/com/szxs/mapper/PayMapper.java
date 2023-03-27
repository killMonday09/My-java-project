package com.szxs.mapper;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.vo.OrderStateVo;
import com.szxs.vo.PayOrderNoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayMapper {

    /**
     * 保存交易信息
     * @return
     */
    int savePay(DmTrade dmTrade);

    /**
     * 根据订单号查询订单支付页面
     * @param orderNo
     * @return
     */
    PayOrderNoVo queryPayOrderNo(@Param("orderNo") String orderNo);


    int updateOrderType(DmOrder dmOrder);

    DmOrder queryOrder(@Param("orderNo") String orderNo);

    int updateSeatStatus(DmSchedulerSeat dmSchedulerSeat);

}
