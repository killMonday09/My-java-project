package com.szxs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderNoVo implements Serializable {
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
    private Integer sourceType;
    private Integer isNeedInvoice;
    private Integer invoiceType;
    private String invoiceHead;
    private String invoiceNo;
    private Integer isNeedInsurance;
    private double totalAmount;
    private double insuranceAmount;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdTime;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date updatedTime;
    private String qrCode;
    private String code;
    private String msg;

}
