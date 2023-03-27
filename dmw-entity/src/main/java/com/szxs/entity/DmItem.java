package com.szxs.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmItem implements Serializable {

  private long id;
  private String itemName;
  private String abstractMessage;
  private Integer itemType1Id;
  private String itemType1Name;
  private Integer itemType2Id;
  private String itemType2Name;
  private Integer state;
  private String basicDescription;
  private String projectDescription;
  private String reminderDescription;
  private String longitude;
  private String latitude;
  private Integer isBanner;
  private Integer isRecommend;
  private double avgScore;
  private Integer commentCount;
  private Integer cinemaId;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;
  private double minPrice;
  private double maxPrice;
  private Integer ageGroup;

  private String address;
  private Integer areaId;
  private String areaName;

  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
  private String imgUrl;

  public DmItem(Integer itemType1Id) {
    this.itemType1Id = itemType1Id;
  }
}
