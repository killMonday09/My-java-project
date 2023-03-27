package com.szxs.feign;

import com.szxs.dto.CinemaSeatArrayDto;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.vo.DmSchedulerVo;
import com.szxs.vo.SchedulerSeatPriceVo;
import com.szxs.vo.SeatInfoListVo;
import com.szxs.vo.SeatPriceListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("dmw-scheduler-provider")
public interface SchedulerClient {

    @RequestMapping("/querySeatArray")
    public DmScheduler querySeatArray(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);

    @RequestMapping("/querySeatArrayByScheduleId")
    public DmScheduler querySeatArrayByScheduleId(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);

    @RequestMapping("/querySeatInfoListVo")
    public List<SeatInfoListVo> querySeatInfoListVo(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);

    @RequestMapping("/querySeatPriceListVo")
    public List<SeatPriceListVo> querySeatPriceListVo(@RequestBody CinemaSeatArrayDto cinemaSeatArrayDto);

    @RequestMapping("/querySchedulerSeatByOrder")
    public DmSchedulerSeat querySchedulerSeatByOrder(@RequestParam("scheduleId") Integer scheduleId,
                                                     @RequestParam("x") Integer x,
                                                     @RequestParam("y") Integer y);

    @RequestMapping("/modifySchedulerSeat")
    public boolean modifySchedulerSeat(@RequestBody DmSchedulerSeat dmSchedulerSeat);

    @RequestMapping("/querySchedulerPriceBySchedulerIdAndArea")
    public DmSchedulerSeatPrice querySchedulerPriceBySchedulerIdAndArea(@RequestParam("scheduleId") Integer scheduleId,
                                                                        @RequestParam("areaLevel") Integer areaLevel);

}
