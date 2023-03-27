package com.szxs.service;

import com.szxs.dto.*;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemType;
import com.szxs.entity.DmScheduler;
import com.szxs.entity.DmSchedulerSeatPrice;
import com.szxs.vo.*;
import com.szxs.dto.ItemLikeDto;
import com.szxs.dto.SearchItemTypeDto;
import com.szxs.vo.ItemDetailVo;
import com.szxs.vo.ItemLikeVo;
import com.szxs.vo.ItemTypeVo;
import com.szxs.vo.ItemVo;
import com.szxs.vo.TransverseVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HomeService {

    /**
     * 查询所有类别
     * @return
     */
    List<ItemTypeVo> queryAllType();

    /**
     * 全文检索查询类别
     * @return
     */
    List<ItemTypeVo> queryItemType(SearchItemTypeDto searchItemTypeDto);

    /**
     * 全文检索查询可能喜欢
     * @param itemLikeDto
     * @return
     */
    List<ItemLikeVo> queryItemLike(ItemLikeDto itemLikeDto);

    /**
     * 根据id查询剧场详情
     * @param itemDetailDto
     * @return
     */
    ItemDetailVo queryItemDetail(ItemDetailDto itemDetailDto);

    /**
     * 2.查询一级分类
     * @return
     */
    List<TransverseVo> queryTransverse();

    /**
     * 3.	轮播图
     * @return
     */
    List<ItemVo> queryBanner();

    /**
     * 4.	今日推荐
     * @return
     */
    List<ItemVo> queryTodayRecommend();

    /**
     * 5.	即将开售
     * @return
     */
    List<ItemVo> queryToSaleItem();

    /**
     * 6.	剧场类型推荐电影（1F/2F。。。）
     * @return
     */
    List<FloorVo> queryFloorItems();

    /**
     * 7.	热门演出推荐排行
     * @return
     */
    List<ItemVo> queryHotItems(ItemTypeIdDto itemTypeIdDto);

    /**
     * 1.音乐会专区
     * @param itemByAgeDto
     * @return
     */
    List<FloorItemVo> queryItemByAge(ItemByAgeDto itemByAgeDto);

    /**
     * 2.	精彩聚集(2)
     * @param advertisingDto
     * @return
     */
    List<FloorItemVo> queryAdvertising(AdvertisingDto advertisingDto);

    /**
     * 3.	轮播图
     * @param itemTypeIdDto
     * @return
     */
    List<ItemVo> querySlideShowPic(ItemTypeIdDto itemTypeIdDto);

    /**
     * 2.	根据id查询剧场评论
     * @param commentDto
     * @return
     */
    List<ItemCommentVo> queryItemComment(CommentDto commentDto);

    /**
     * 查询排期表
     * @param dmScheduler
     * @return
     */
    List<DmSchedulerVo> queryScheduler(@RequestBody DmScheduler dmScheduler);

    /**
     * 查询座位价格
     * @param dmSchedulerSeatPrice
     * @return
     */
    List<SchedulerSeatPriceVo> queryPrice(@RequestBody DmSchedulerSeatPrice dmSchedulerSeatPrice);

    /**
     * 5.	精彩聚集(1)
     * @param itemLikeDto
     * @return
     */
    List<FloorItemVo> queryItemNice(@RequestBody ItemLikeDto itemLikeDto);

    /**
     * 6.	热门排行
     * @param itemHotDto
     * @return
     */
    List<FloorItemVo> queryItemHot(@RequestBody ItemHotDto itemHotDto);

    List<ItemByMonthVo> queryItemByMonth(ItemByMonthDto itemByMonthDto);
}
