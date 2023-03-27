package com.szxs.controller;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.mapper.SchedulerMapper;
import com.szxs.vo.DmSchedulerVo;
import com.szxs.vo.SchedulerSeatPriceVo;
import com.szxs.vo.SeatInfoListVo;
import com.szxs.vo.SeatPriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulerController {
    @Autowired
    private SchedulerMapper schedulerMapper;

    @RequestMapping("/queryScheduler")
    public List<DmSchedulerVo> queryScheduler(@RequestBody DmScheduler dmScheduler){
        return schedulerMapper.querySchedulerVo(dmScheduler);
    }

    @RequestMapping("/queryPrice")
    public List<SchedulerSeatPriceVo> queryPrice(@RequestBody DmSchedulerSeatPrice dmSchedulerSeatPrice){
        return schedulerMapper.queryPrice(dmSchedulerSeatPrice);
    }


}
