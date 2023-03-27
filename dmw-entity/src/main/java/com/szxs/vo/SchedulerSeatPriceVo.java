package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerSeatPriceVo {
    private Integer id;
    private Integer isHaveSeat;
    private double price;
    private Integer scheduleId;
}
