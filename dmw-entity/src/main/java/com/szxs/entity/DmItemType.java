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
public class DmItemType implements Serializable {

  private Integer id;
  private String itemType;
  private String level;
  private Integer parent;
  private String aliasName;
  private String key;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;

  public DmItemType(Integer id) {
    this.id = id;
  }

  public DmItemType(String level) {
    this.level = level;
  }

  public DmItemType(String level, Integer parent) {
    this.level = level;
    this.parent = parent;
  }
}
