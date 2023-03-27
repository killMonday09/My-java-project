package com.szxs.service;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.vo.CinemaSeatArrayVo;
import com.szxs.vo.DmSchedulerVo;
import com.szxs.vo.SchedulerSeatPriceVo;
import com.szxs.vo.SeatArrayByScheduleIdVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SchedulerService {



    CinemaSeatArrayVo querySeatArray(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);

    /**
     * 查询
     * @param cinemaSeatArrayDto
     * @return
     */
    SeatArrayByScheduleIdVo querySeatArrayByScheduleIdList(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);
}
