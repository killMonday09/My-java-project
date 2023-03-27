package com.szxs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmCinema implements Serializable {

  private Integer id;
  private String name;
  private String address;
  private Integer areaId;
  private String areaName;
  private Integer xLength;
  private Integer yLength;
  private String latitude;
  private String longitude;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;

}
