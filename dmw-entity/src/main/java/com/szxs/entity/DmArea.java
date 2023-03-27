package com.szxs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmArea implements Serializable {

  private Integer id;
  private String name;
  private String parent;
  private Integer level;
  private Integer isHot;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;

  public DmArea(Integer id) {
    this.id = id;
  }
}
