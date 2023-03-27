package com.szxs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登陆成功的令牌信息
 */
@Data
public class RespTokenLoginVo{
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int expTime; //令牌过期时间
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date genTime; //生成令牌时间
    private String token; //令牌
}
