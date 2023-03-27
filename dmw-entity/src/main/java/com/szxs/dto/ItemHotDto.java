package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Rich
 * @create 2022/10/26 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemHotDto implements Serializable {
    private Integer areaId;
    private Integer itemTypeId;
    private Integer limit;
    private Integer itemType1Id;
}
