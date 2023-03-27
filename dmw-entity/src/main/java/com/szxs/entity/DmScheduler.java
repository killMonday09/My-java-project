package com.szxs.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmScheduler implements Serializable {

  private Integer id;
  private String title;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;
  private Integer itemId;
  private Integer cinemaId;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createdTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updatedTime;


}
