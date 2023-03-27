package com.szxs.controller;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.service.SchedulerService;
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
@RequestMapping("/api/p")
@Log4j2
public class SchedulerController {
    @Autowired
    private SchedulerService service;

    @PostMapping("/queryOriginalCinemaSeatArray")
    public RespDataVo queryOriginalCinemaSeatArray(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        try {
            RespDataVo success = VoUtil.getSuccess(service.querySeatArray(cinemaSeatArrayDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

    @PostMapping("/queryCinemaSeatArrayByScheduleId")
    public RespDataVo queryCinemaSeatArrayByScheduleId(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        try {
            RespDataVo success = VoUtil.getSuccess(service.querySeatArrayByScheduleIdList(cinemaSeatArrayDto));
            success.setErrorCode("0000");
            return success;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }

}
