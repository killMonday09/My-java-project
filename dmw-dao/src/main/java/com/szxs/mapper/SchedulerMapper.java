package com.szxs.mapper;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.vo.DmSchedulerVo;
import com.szxs.vo.SchedulerSeatPriceVo;
import com.szxs.vo.SeatInfoListVo;
import com.szxs.vo.SeatPriceListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchedulerMapper {

    List<DmSchedulerVo> querySchedulerVo(DmScheduler dmScheduler);

    List<SchedulerSeatPriceVo> queryPrice(DmSchedulerSeatPrice dmSchedulerSeatPrice);

    DmScheduler querySeatArray(CinemaSeatArrayDto cinemaSeatArrayDto);

    DmScheduler querySeatArrayByScheduleId(CinemaSeatArrayDto cinemaSeatArrayDto);

    List<SeatInfoListVo> querySeatInfoListVo(CinemaSeatArrayDto cinemaSeatArrayDto);

    List<SeatPriceListVo> querySeatPriceListVo(CinemaSeatArrayDto cinemaSeatArrayDto);

    /**
     * 订单功能查询座位x和y
     * @param scheduleId
     * @param x
     * @param y
     * @return
     */
    DmSchedulerSeat querySchedulerSeatByOrder(@Param("scheduleId") Integer scheduleId,
                                              @Param("x") Integer x,
                                              @Param("y") Integer y);

    int ModifySchedulerSeat(DmSchedulerSeat dmSchedulerSeat);

    DmSchedulerSeatPrice querySchedulerPriceBySchedulerIdAndArea(@Param("scheduleId") Integer scheduleId,
                                                                 @Param("areaLevel") Integer areaLevel);
}
