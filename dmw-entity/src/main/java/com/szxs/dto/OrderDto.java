package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    /**
     * 影院id
     */
    private Integer cinemaId;
    /**
     * 发票抬头
     */
    private String invoiceHead;
    /**
     * 发票号
     */
    private String invoiceNo;
    /**
     * 发票类型
     */
    private Integer invoiceType;
    /**
     * 是否需要保险
     */
    private Integer isNeedInsurance;
    /**
     * 是否需要发票
     */
    private Integer isNeedInvoice;
    /**
     * 剧场id
     */
    private Integer itemId;
    /**
     * 用户联系人
     */
    private String linkIds;
    /**
     * 播放时间id
     */
    private Integer schedulerId;
    /**
     * 座位位置
     */
    private String seatPositions;
    /**
     * 来源类型
     */
    private Integer sourceType;
    /**
     * 用户id
     */
    private Integer userId;

}

