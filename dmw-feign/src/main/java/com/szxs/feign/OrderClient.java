package com.szxs.feign;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.dto.OrderListDto;
import com.szxs.entity.*;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderListVo;
import com.szxs.vo.OrderStateVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("dmw-order-provider")
public interface OrderClient {

    @PostMapping("/createOrder")
    public int createOrder(@RequestBody DmOrder dmOrder);

    @PostMapping("/modifyOrder")
    public int modifyOrder(@RequestBody DmOrder dmOrder);

    @PostMapping("/queryOrderByOrderNo")
    public OrderByOrderNoVo queryOrderByOrderNo(@RequestBody OrderByOrderNoDto orderByOrderNoDto);

    @PostMapping("/addOrderLinkUser")
    public boolean addOrderLinkUser(@RequestBody DmOrderLinkUser dmOrderLinkUser);

    @RequestMapping("/updateSeatNum")
    public boolean updateSeatNum(@RequestParam("areaLevel") Integer areaLevel,
                                 @RequestParam("scheduleId") Integer scheduleId,
                                 @RequestParam("seatNum") Integer seatNum);

    @RequestMapping("/queryOrderList")
    public List<OrderListVo> queryOrderList(@RequestBody OrderListDto orderListDto);

    @PostMapping("/queryOrderState")
    OrderStateVo queryOrderState(@RequestBody OrderByOrderNoDto orderByOrderNoDto);

}
