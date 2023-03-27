package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatArrayByScheduleIdVo implements Serializable {

    private Integer cinemaId;
    private Integer scheduleId;
    private List<SeatInfoListVo> seatInfoList;
    private List<SeatPriceListVo> seatPriceList;

}
