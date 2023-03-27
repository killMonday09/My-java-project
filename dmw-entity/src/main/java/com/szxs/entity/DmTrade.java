package com.szxs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmTrade implements Serializable {

  private String id;
  private String orderNo;
  private String tradeNo;
  private Integer payMethod;
  private double amount;
  private Date createdTime;
  private Date updatedTime;

}
