package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserDto implements Serializable {
    private Date birthday;
    private String hobby;
    private Integer id;
    private String idCard;
    private String imgUrl;
    private String nickName;
    private String phone;
    private String realName;
    private Integer sex;

}
