package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemByMonthDto implements Serializable {

    private Integer itemTypeId;
    private Integer month;
    private Integer year;
}
