package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfoListVo implements Serializable {
    private Integer id;
    private Integer x;
    private Integer y;
    private Integer areaLevel;
    private Integer cinemaId;
    private Integer sort;
    private Integer status;
}
