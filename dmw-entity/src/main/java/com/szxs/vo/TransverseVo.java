package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @Author Rich
 * @create 2022/10/18 15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransverseVo {
    private String aliasName;
    private LocalTime createdTime;
    private long id;
    private String itemType;
    private String key;
    private String level;
    private long parent;
    private LocalTime updatedTime;
}
