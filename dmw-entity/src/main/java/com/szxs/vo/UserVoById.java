package com.szxs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoById implements Serializable {
    private Integer id;
    private String phone;
    private String password;
    private String imgUrl;
    private String wxUserId;
    private String realName;
    private String nickName;
    private Integer sex;
    private String hobby;
    private String idCard;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date birthday;
}
