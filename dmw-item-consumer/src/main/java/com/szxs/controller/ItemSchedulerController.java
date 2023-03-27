package com.szxs.controller;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.dto.ItemHotDto;
import com.szxs.dto.ItemLikeDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.service.HomeService;
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
@RequestMapping("/api/p/")
@Log4j2
public class ItemSchedulerController {
    @Autowired
    private HomeService service;

    @PostMapping("/queryItemScheduler")
    public RespDataVo queryItemScheduler(@RequestBody DmScheduler dmScheduler){
        try {
            RespDataVo success = VoUtil.getSuccess(service.queryScheduler(dmScheduler));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemPrice")
    public RespDataVo queryItemPrice(@RequestBody DmSchedulerSeatPrice dmSchedulerSeatPrice){
        try {
            RespDataVo success = VoUtil.getSuccess(service.queryPrice(dmSchedulerSeatPrice));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemNice")
    public RespDataVo queryItemNice(@RequestBody ItemLikeDto likeDto){
        try {
            RespDataVo success = VoUtil.getSuccess(service.queryItemNice(likeDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemHot")
    public RespDataVo queryItemHot(@RequestBody ItemHotDto itemHotDto){
        try {
            RespDataVo success = VoUtil.getSuccess(service.queryItemHot(itemHotDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }
}
