package com.szxs.service;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.dto.OrderDto;
import com.szxs.dto.OrderListDto;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderListVo;
import com.szxs.vo.OrderStateVo;
import com.szxs.vo.OrderVo;

import java.util.List;

public interface OrderService {


    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    OrderVo createOrder(OrderDto orderDto);

    OrderByOrderNoVo OrderByOrderNo(OrderByOrderNoDto orderByOrderNoDto);

    List<OrderListVo> queryOrderList(OrderListDto orderListDto);

    OrderStateVo queryOrderState(OrderByOrderNoDto orderByOrderNoDto);
}
