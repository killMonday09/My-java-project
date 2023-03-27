package com.szxs.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DmUser implements Serializable {
  private Integer id;
  private String phone;
  private String password;
  private String wxUserId;
  private String realName;
  private String nickName;
  private Integer sex;
  private String hobby;
  private String idCard;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date birthday;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createdTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updatedTime;
  //private Integer userId;

}
