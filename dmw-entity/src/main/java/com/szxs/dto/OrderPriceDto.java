package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPriceDto implements Serializable {
    private Integer itemId;
    private Integer x;
    private Integer y;
    private Integer schedulerId;
    private String seatPositions;
}
