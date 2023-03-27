package com.szxs.controller;

import com.szxs.dto.AdvertisingDto;
import com.szxs.dto.ItemTypeIdDto;
import com.szxs.dto.SearchItemTypeDto;
import com.szxs.service.HomeService;
import com.szxs.util.DmwConstant;
import com.szxs.util.VoUtil;
import com.szxs.vo.ItemVo;
import com.szxs.vo.RespDataVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/p/index")
@Log4j2
public class HomeController {
    @Autowired
    private HomeService homeService;

    @PostMapping("/queryAllType")
    public RespDataVo queryAllType(){
        try {
            RespDataVo respDataVo = VoUtil.getSuccess(homeService.queryAllType());
            respDataVo.setSuccess("true");
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryTransverse")
    public RespDataVo queryTransverse(){
        try {
            return VoUtil.getSuccess(homeService.queryTransverse());
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryBanner")
    public RespDataVo queryBanner(){
        try {
            return VoUtil.getSuccess(homeService.queryBanner());
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryTodayRecommend")
    public RespDataVo queryTodayRecommend(){
        try {
            return VoUtil.getSuccess(homeService.queryTodayRecommend());
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryToSaleItem")
    public RespDataVo queryToSaleItem(){
        try {
            return VoUtil.getSuccess(homeService.queryToSaleItem());
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }


    @PostMapping("/queryFloorItems")
    public RespDataVo queryFloorItems(){
        try {
            return VoUtil.getSuccess(homeService.queryFloorItems());
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }



    @PostMapping("/queryHotItems")
    public RespDataVo queryHotItems(@RequestBody ItemTypeIdDto itemTypeId){
        try {
            return VoUtil.getSuccess(homeService.queryHotItems(itemTypeId));
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }



}
