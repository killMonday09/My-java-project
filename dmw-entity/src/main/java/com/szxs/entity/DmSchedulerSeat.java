package com.szxs.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmSchedulerSeat {

  private Integer id;
  private Integer x;
  private Integer y;
  private Integer areaLevel;
  private Integer scheduleId;
  private String orderNo;
  private Integer userId;
  private Integer status;
  private Integer sort;
  private Date createdTime;
  private Date updatedTime;

  public DmSchedulerSeat(Integer areaLevel, Integer scheduleId) {
    this.areaLevel = areaLevel;
    this.scheduleId = scheduleId;
  }

  public DmSchedulerSeat(String orderNo, Integer status,Date updatedTime) {
    this.orderNo = orderNo;
    this.status = status;
    this.updatedTime = updatedTime;
  }
}
