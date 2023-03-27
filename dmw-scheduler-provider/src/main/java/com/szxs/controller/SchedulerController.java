package com.szxs.controller;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.mapper.SchedulerMapper;
import com.szxs.vo.DmSchedulerVo;
import com.szxs.vo.SchedulerSeatPriceVo;
import com.szxs.vo.SeatInfoListVo;
import com.szxs.vo.SeatPriceListVo;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulerController {
    @Autowired
    private SchedulerMapper schedulerMapper;


    @RequestMapping("/querySeatArray")
    public DmScheduler querySeatArray(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        return schedulerMapper.querySeatArray(cinemaSeatArrayDto);
    }

    @RequestMapping("/querySeatArrayByScheduleId")
    public DmScheduler querySeatArrayByScheduleId(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        return schedulerMapper.querySeatArrayByScheduleId(cinemaSeatArrayDto);
    }
    @RequestMapping("/querySeatInfoListVo")
    public List<SeatInfoListVo> querySeatInfoListVo(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        return schedulerMapper.querySeatInfoListVo(cinemaSeatArrayDto);
    }
    @RequestMapping("/querySeatPriceListVo")
    public List<SeatPriceListVo> querySeatPriceListVo(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto){
        return schedulerMapper.querySeatPriceListVo(cinemaSeatArrayDto);
    }

    @RequestMapping("/querySchedulerSeatByOrder")
    public DmSchedulerSeat querySchedulerSeatByOrder(@RequestParam("scheduleId") Integer scheduleId,
                                                     @RequestParam("x") Integer x,
                                                     @RequestParam("y") Integer y){
        return schedulerMapper.querySchedulerSeatByOrder(scheduleId,x,y);
    }

    @RequestMapping("/modifySchedulerSeat")
    public boolean modifySchedulerSeat(@RequestBody DmSchedulerSeat dmSchedulerSeat){
        return schedulerMapper.ModifySchedulerSeat(dmSchedulerSeat) > 0;
    }

    @RequestMapping("/querySchedulerPriceBySchedulerIdAndArea")
    public DmSchedulerSeatPrice querySchedulerPriceBySchedulerIdAndArea(@RequestParam("scheduleId") Integer scheduleId,
                                                                        @RequestParam("areaLevel") Integer areaLevel){
        return schedulerMapper.querySchedulerPriceBySchedulerIdAndArea(scheduleId,areaLevel);
    }


}
