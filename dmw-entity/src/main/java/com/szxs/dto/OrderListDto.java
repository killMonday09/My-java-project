package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto implements Serializable {
    private Integer userId;
    private Integer orderType;
    private Integer orderTime;
    private String keyword;
}
