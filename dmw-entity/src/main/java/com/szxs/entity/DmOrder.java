package com.szxs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DmOrder implements Serializable {

    private Integer id;
    private String orderNo;
    private Integer userId;
    private Integer schedulerId;
    private Integer itemId;
    private String itemName;
    private String wxTradeNo;
    private String aliTradeNo;
    private Integer orderType;
    private String payType;
    private Integer totalCount;
    private Integer area;
    private Integer sourceType;
    private Integer isNeedInvoice;
    private Integer invoiceType;
    private String invoiceHead;
    private String invoiceNo;
    private Integer isNeedInsurance;
    private double totalAmount;
    private double insuranceAmount;

    private Date createdTime;
    private Date updatedTime;
    private String seatPositions;

    public DmOrder(String orderNo, String aliTradeNo, Integer orderType, Date updatedTime) {
        this.orderNo = orderNo;
        this.aliTradeNo = aliTradeNo;
        this.orderType = orderType;
        this.updatedTime = updatedTime;
    }
}

