package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLikeDto implements Serializable {

    private Integer itemTypeId;
    private Integer limit;
    private Integer itemType1Id;
    private Integer itemType2Id;
}
