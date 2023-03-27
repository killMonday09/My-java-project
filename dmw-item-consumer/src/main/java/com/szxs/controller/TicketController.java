package com.szxs.controller;

import com.szxs.dto.*;
import com.szxs.entity.DmItem;
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

import java.util.List;

@RestController
@RequestMapping("/api/p")
@Log4j2
public class TicketController {

    @Autowired
    private HomeService homeService;

    @PostMapping("/queryItemLike")
    public RespDataVo queryItemLike(@RequestBody ItemLikeDto itemLikeDto){
        try {

            RespDataVo respDataVo = VoUtil.getSuccess(homeService.queryItemLike(itemLikeDto));
            respDataVo.setSuccess("true");
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemDetail")
    public RespDataVo queryItemDetail(@RequestBody ItemDetailDto itemDetailDto){
        try {
            RespDataVo respDataVo = VoUtil.getSuccess(homeService.queryItemDetail(itemDetailDto));
            respDataVo.setSuccess("true");
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemByAge")
    public RespDataVo queryItemByAge(@RequestBody ItemByAgeDto itemByAgeDto){
        try {
            return VoUtil.getSuccess(homeService.queryItemByAge(itemByAgeDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/querySlideShowPic")
    public RespDataVo querySlideShowPic(@RequestBody ItemTypeIdDto itemTypeIdDto){
        try {
            return VoUtil.getSuccess(homeService.querySlideShowPic(itemTypeIdDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/type/queryItemType")
    public RespDataVo queryItemType(@RequestBody SearchItemTypeDto searchItemTypeDto){
        try {
            RespDataVo respDataVo = VoUtil.getSuccess(homeService.queryItemType(searchItemTypeDto));
            respDataVo.setSuccess("true");
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemComment")
    public RespDataVo queryItemComment(@RequestBody CommentDto commentDto){
        try {
            return VoUtil.getSuccess(homeService.queryItemComment(commentDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryAdvertising")
    public RespDataVo queryAdvertising(@RequestBody AdvertisingDto advertisingDto){
        try {
            return VoUtil.getSuccess(homeService.queryAdvertising(advertisingDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryItemByMonth")
    public RespDataVo queryItemByMonth(@RequestBody ItemByMonthDto itemByMonthDto) {
        try {
            RespDataVo success = VoUtil.getSuccess(homeService.queryItemByMonth(itemByMonthDto));
            success.setErrorCode(DmwConstant.SUCCESS_CODE);
            return success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE, e.getMessage());
        }
    }
}
