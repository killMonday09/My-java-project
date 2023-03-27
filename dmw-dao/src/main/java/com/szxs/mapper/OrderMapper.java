package com.szxs.mapper;

import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.dto.OrderListDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmOrderLinkUser;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.OrderListVo;
import com.szxs.vo.OrderStateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    DmOrder queryOrderByOrder(String orderNo);

    /**
     * 监听队列后修改支付状态
     * @param orderNo
     * @param orderType
     * @return
     */
    int updateOrderType(@Param("orderNo") String orderNo,@Param("orderType") Integer orderType);

    /**
     * 保存订单
     * @param dmOrder
     * @return
     */
    int saveOrder(DmOrder dmOrder);

    /**
     * 修改订单状态和座位状态
     * @param dmOrder
     * @return
     */
    int updateOrder(DmOrder dmOrder);

    /**
     * 根据订单号查询
     * @param orderByOrderNoDto
     * @return
     */
    OrderByOrderNoVo queryOrderByOrderNo(OrderByOrderNoDto orderByOrderNoDto);

    /**
     * 插入订单常用联系人
     * @param dmOrderLinkUser
     * @return
     */
    int addOrderLinkUser(DmOrderLinkUser dmOrderLinkUser);

    /**
     * 根据订单号查询订单状态
     * @param orderByOrderNoDto
     * @return
     */
    OrderStateVo queryOrderState(OrderByOrderNoDto orderByOrderNoDto);

    /**
     * 减库存
     * @param areaLevel
     * @param scheduleId
     * @param seatNum
     * @return
     */
    int updateSeatNum(@Param("areaLevel") Integer areaLevel,
                      @Param("scheduleId") Integer scheduleId,
                      @Param("seatNum") Integer seatNum);

    /**
     * 加库存
     * @param areaLevel
     * @param scheduleId
     * @param seatNum
     * @return
     */
    int updateAddSeatNum(@Param("areaLevel") Integer areaLevel,
                      @Param("scheduleId") Integer scheduleId,
                      @Param("seatNum") Integer seatNum);

    /**
     * 修改座位状态
     * @param orderNo
     * @param scheduleId
     * @return
     */
    int updateSeatStatus(@Param("orderNo") String orderNo ,@Param("scheduleId") Integer scheduleId);

    /**
     * 查询订单详情
     * @param orderListDto
     * @return
     */
    List<OrderListVo> queryOrderList(OrderListDto orderListDto);


}
