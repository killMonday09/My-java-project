package com.szxs.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmLinkUser {

  private Integer id;
  private Integer userId;
  private String name;
  private String idCard;
  private Integer cardType;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createdTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updatedTime;

}
