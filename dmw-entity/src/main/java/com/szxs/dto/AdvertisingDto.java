package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Rich
 * @create 2022/10/24 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisingDto implements Serializable {
    private Integer itemTypeId;
    private Integer limit;
}
