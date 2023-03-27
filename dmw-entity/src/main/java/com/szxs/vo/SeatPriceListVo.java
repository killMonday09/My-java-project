package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.dc.pr.PRError;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatPriceListVo implements Serializable {
    private String areaLevelName;
    private Integer price;
    private Integer areaLevel;
}
