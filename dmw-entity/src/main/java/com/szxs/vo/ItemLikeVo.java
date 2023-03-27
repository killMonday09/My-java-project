package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLikeVo implements Serializable {

    private String address;
    private Integer areaId;
    private String areaName;
    private LocalDateTime endDate;
    private Integer id;
    private String imgUrl;
    private String itemName;
    private Double minPrice;
    private LocalDateTime startDate;

}
