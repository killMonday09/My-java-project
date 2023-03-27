package com.szxs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkUserVo implements Serializable {
    private Integer id;
    private String idCard;
    private String name;
    private Integer cardType;
}
