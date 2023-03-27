package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Rich
 * @create 2022/10/24 13:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTypeIdDto implements Serializable {
    private Integer itemTypeId;
}
