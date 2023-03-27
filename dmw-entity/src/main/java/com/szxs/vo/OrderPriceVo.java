package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPriceVo implements Serializable {
    private String itemName;
    private String seatNames;
    private Integer seatCount;
    private double totalAmount;
}
