package com.szxs.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应的公共对象
 * @param <T>
 */
@Data
public class RespDataVo<T> implements Serializable {
    private String errorCode;
    private String msg;
    private String success;
    private T data;
}
