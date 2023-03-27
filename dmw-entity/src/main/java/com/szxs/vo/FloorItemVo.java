package com.szxs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author Rich
 * @create 2022/10/20 11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorItemVo implements Serializable {
    /**
     * 电影院地址
     */
    private String address;
    /**
     * 地点id
     */
    private long areaId;
    /**
     *地点名称
     */
    private String areaName;
    /**
     * 节目结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    /**
     * 节目id
     */
    private long id;
    /**
     * 节目名称
     */
    private String itemName;
    /**
     * 节目最小价格
     */
    private double minPrice;
    /**
     * 节目开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    /**
     * 图片路径
     */
    private String imgUrl;
}
