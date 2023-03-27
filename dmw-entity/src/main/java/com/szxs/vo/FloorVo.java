package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rich
 * @create 2022/10/20 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorVo implements Serializable {
    private Integer floor;
    private String itemTypeName;
    private Integer itemTypeId;
    private List<FloorItemVo> items;
}
