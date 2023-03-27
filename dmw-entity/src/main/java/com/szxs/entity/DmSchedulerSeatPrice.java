package com.szxs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmSchedulerSeatPrice {

  private Integer id;
  private double price;
  private Integer areaLevel;
  private Integer scheduleId;
  private Integer seatNum;
  private Date createdTime;
  private Date updatedTime;

}
