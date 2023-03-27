package com.szxs.feign;

import com.szxs.dto.*;
import com.szxs.entity.DmImage;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemType;
import com.szxs.vo.FloorVo;
import com.szxs.vo.ItemDetailVo;
import com.szxs.vo.ItemLikeVo;
import com.szxs.vo.ItemTypeVo;
import com.szxs.entity.*;
import com.szxs.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("dmw-item-provider")
public interface ItemTypeClient {

    /**
     * 查询分类集合
     * @param dmItemType
     * @return
     */
    @PostMapping("/queryItemTypeList")
    List<DmItemType> queryItemTypeList(@RequestBody DmItemType dmItemType);

    /**
     * 查询查询节目集合
     * @param item
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/queryItemList")
    List<DmItem> queryItemList(@RequestBody DmItem item,
                               @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                               @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize);

    /**
     *查询可能喜欢
     * @param
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/queryItemListByBanner")
    List<DmItem> queryItemListByBanner(@RequestBody DmItem item,
                                       @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);

    @PostMapping("/queryTodayRecommend")
    List<DmItem> queryTodayRecommend(@RequestBody DmItem item,
                                     @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                     @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize);

    @PostMapping("/queryDmImage")
    DmImage queryDmImage(@RequestParam("id") long id);

    @PostMapping("/queryItemLike")
    List<ItemLikeVo> queryItemLike(@RequestBody ItemLikeDto itemLikeDto,
                                   @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize);

    /**
     * 根据id查询剧场详情
     * @param itemDetailDto
     * @return
     */
    @PostMapping("/queryItemDetail")
    ItemDetailVo queryItemDetail(@RequestBody ItemDetailDto itemDetailDto);

    @PostMapping("/queryFloorItems")
    List<DmItem> queryFloorItems(@RequestBody DmItem dmItem,
                                      @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    );

    @PostMapping("/queryFloorList")
    List<FloorVo> queryFloorList(@RequestBody FloorVo floorVo);

    @PostMapping("/queryItemType")
    List<ItemTypeVo> queryItemType(@RequestBody SearchItemTypeDto searchItemTypeDto);

    /**
     * 2.	根据id查询剧场评论
     * @param commentDto
     * @return
     */
    @PostMapping("/queryItemComment")
    List<DmItemComment> queryItemComment(@RequestBody CommentDto commentDto);

    @RequestMapping("/queryScheduler")
    List<DmSchedulerVo> queryScheduler(@RequestBody DmScheduler dmScheduler);

    @RequestMapping("/queryPrice")
    List<SchedulerSeatPriceVo> queryPrice(@RequestBody DmSchedulerSeatPrice dmSchedulerSeatPrice);

    @PostMapping("/queryItemHot")
    List<DmItem> queryItemHot(@RequestBody ItemHotDto itemHotDto,
                              @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);

    @PostMapping("/queryItemByMonth")
    List<ItemByMonthVo> queryItemByMonth(@RequestBody ItemByMonthDto itemByMonthDto);

    @PostMapping("/queryItemNameById")
    DmItem queryItemNameById(@RequestParam("id") Integer id);
}
