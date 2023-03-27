package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTypeVo implements Serializable {

    private Integer id;
    private String itemType;
    private String level;
    private Integer parent;
    private String aliasName;
    private String key;
    private List<ItemTypeVo> children;
    private List<ItemVo> hotItems;
}
