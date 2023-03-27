package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailVo implements Serializable {

    private String abstractMessage;
    private String address;
    private Integer areaId;
    private String areaName;
    private Double avgScore;
    private String basicDescription;
    private Integer cinemaId;
    private Integer commentCount;
    private LocalDateTime endTime;
    private Integer id;
    private String imgUrl;
    private String itemName;
    private Integer itemType1Id;
    private String itemType1Name;
    private Integer itemType2Id;
    private String itemType2Name;
    private String latitude;
    private String longitude;
    private String projectDescription;
    private String reminderDescription;
    private LocalDateTime startTime;
    private String state;
}
