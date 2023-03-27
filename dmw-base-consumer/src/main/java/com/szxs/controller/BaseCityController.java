package com.szxs.controller;

import com.szxs.entity.DmArea;
import com.szxs.service.BaseCityService;
import com.szxs.util.DmwConstant;
import com.szxs.util.VoUtil;
import com.szxs.vo.RespDataVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/area")
@Log4j2
public class BaseCityController {
    @Autowired
    private BaseCityService baseCityService;

    @PostMapping("/queryCityList")
    public RespDataVo queryCityList(@RequestBody DmArea dmArea){
        try {
            RespDataVo respDataVo = VoUtil.getSuccess(baseCityService.queryCity(dmArea));
            respDataVo.setSuccess("true");
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }
}
