package com.szxs.util;


import com.szxs.vo.RespDataVo;

public class VoUtil {

    public static RespDataVo getSuccess(Object data){
        RespDataVo vo = new RespDataVo();
        vo.setSuccess("true");
        vo.setErrorCode(DmwConstant.SUCCESS_CODE);
        vo.setErrorCode("0");
        vo.setData(data);
        return vo;
    }

    public static RespDataVo getFailed(String code,String msg){
        RespDataVo vo = new RespDataVo();
        vo.setMsg(msg);
        vo.setErrorCode(code);
        vo.setSuccess("false");
        return vo;
    }
}
