package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo implements Serializable {

    private long id;
    private String itemName;
    private double minPrice;
    private String imgUrl;
}
