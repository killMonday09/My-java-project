package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Rich
 * @create 2022/10/24 15:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemByAgeDto implements Serializable {
    private Integer ageGroup;
    private Integer limit;
}
