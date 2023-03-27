package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkUserDto implements Serializable {
    private String cardType;
    private String idCard;
    private String name;
    private String userId;
    private Integer id;
}
