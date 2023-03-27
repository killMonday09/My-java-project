package com.szxs.mapper;

import com.szxs.dto.CommentDto;
import com.szxs.dto.CommentDto;
import com.szxs.dto.ItemByMonthDto;
import com.szxs.dto.ItemDetailDto;
import com.szxs.dto.ItemHotDto;
import com.szxs.dto.ItemLikeDto;
import com.szxs.entity.DmCinema;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemComment;
import com.szxs.entity.DmItemType;
import com.szxs.vo.ItemByMonthVo;
import com.szxs.vo.ItemDetailVo;
import com.szxs.vo.ItemLikeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<DmItem> queryItemList(DmItem item);

    List<ItemLikeVo> queryItemLike(ItemLikeDto itemLikeDto);

    ItemDetailVo queryItemDetail(ItemDetailDto itemDetailDto);

    List<DmItem> queryItemListByBanner(DmItem item);

    List<DmItem> queryTodayRecommend(DmItem item);

    List<DmItemComment> queryItemComment(CommentDto commentDto);

    List<DmItem> queryItemHot(ItemHotDto itemHotDto);

    DmItem queryItemNameById(@Param("id") Integer id);

    List<ItemByMonthVo> queryItemByMonth(ItemByMonthDto itemByMonthDto);

}
