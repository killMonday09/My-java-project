package com.szxs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmOrderLinkUser implements Serializable {

  private Integer id;
  private Integer orderId;
  private Integer linkUserId;
  private String linkUserName;
  private Integer X;
  private Integer Y;
  private double price;
  private LocalDateTime createdTime;
  private Integer updatedTime;


}
