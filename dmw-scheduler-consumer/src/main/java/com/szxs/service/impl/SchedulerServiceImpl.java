package com.szxs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.feign.SchedulerClient;
import com.szxs.service.SchedulerService;
import com.szxs.util.DmwConstant;
import com.szxs.util.EmptyUtil;
import com.szxs.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SchedulerClient schedulerClient;


    public CinemaSeatArrayVo querySeatArray(CinemaSeatArrayDto cinemaSeatArrayDto) {
        CinemaSeatArrayVo cinemaSeatArrayVo = new CinemaSeatArrayVo();
        DmScheduler scheduler = schedulerClient.querySeatArray(cinemaSeatArrayDto);
        cinemaSeatArrayVo.setCinemaId(scheduler.getCinemaId());

        String[] seatArrays = null;
        if (scheduler.getCinemaId() == 1) {
            seatArrays = new String[]{"bbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbb", "ccccccccccccccc"
                    , "ccccccccccccccc", "ccccccccccccccc", "ccccccccccccccc", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa"};
        }

        if (scheduler.getCinemaId() == 2) {
            seatArrays = new String[]{"bbbbcccccccbb", "ccbbbcccccbbb",
                    "ccbbbcccccbbb", "ccbbbcccccbbb", "cccbbccccbbbb"};
        }
        cinemaSeatArrayVo.setSeatArray(seatArrays);
        return cinemaSeatArrayVo;
    }

    public SeatArrayByScheduleIdVo querySeatArrayByScheduleIdList(CinemaSeatArrayDto cinemaSeatArrayDto) {

        SeatArrayByScheduleIdVo seatArrayByScheduleIdVo = null;

        if (EmptyUtil.isNotEmpty(cinemaSeatArrayDto)) {
            seatArrayByScheduleIdVo = new SeatArrayByScheduleIdVo();
            seatArrayByScheduleIdVo.setScheduleId(cinemaSeatArrayDto.getScheduleId());
            DmScheduler scheduler = schedulerClient.querySeatArrayByScheduleId(cinemaSeatArrayDto);
            List<SeatInfoListVo> seatInfoListVos = schedulerClient.querySeatInfoListVo(cinemaSeatArrayDto);
            List<SeatPriceListVo> seatPriceListVos = schedulerClient.querySeatPriceListVo(cinemaSeatArrayDto);

            for (SeatPriceListVo volist : seatPriceListVos) {
                if (volist.getAreaLevel() == DmwConstant.User.LEVEL1 && volist.getAreaLevel() != null) {
                    volist.setAreaLevelName("A");
                }

                if (volist.getAreaLevel() == DmwConstant.User.LEVEL2 && volist.getAreaLevel() != null) {
                    volist.setAreaLevelName("B");
                }

                if (volist.getAreaLevel() == DmwConstant.User.LEVEL3 && volist.getAreaLevel() != null) {
                    volist.setAreaLevelName("C");
                }
            }

            seatArrayByScheduleIdVo.setCinemaId(scheduler.getCinemaId());
            seatArrayByScheduleIdVo.setSeatInfoList(seatInfoListVos);
            seatArrayByScheduleIdVo.setSeatPriceList(seatPriceListVos);
        }
        return seatArrayByScheduleIdVo;
    }

}

