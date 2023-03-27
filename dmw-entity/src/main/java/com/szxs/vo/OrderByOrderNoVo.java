package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderByOrderNoVo implements Serializable {

    /**
     * 剧场名
     */
    private String itemName;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 座位数
     */
    private Integer seatCount;
    /**
     * x=排  y=座
     */
    private String seatName;
    /**
     * 总金额
     */
    private Integer totalAmount;

}
