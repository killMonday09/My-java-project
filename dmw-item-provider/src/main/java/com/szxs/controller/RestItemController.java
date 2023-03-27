package com.szxs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szxs.dto.CommentDto;
import com.szxs.dto.ItemByMonthDto;
import com.szxs.dto.ItemDetailDto;
import com.szxs.dto.ItemHotDto;
import com.szxs.dto.ItemLikeDto;
import com.szxs.entity.DmCinema;
import com.szxs.entity.DmImage;
import com.szxs.entity.DmItem;
import com.szxs.entity.DmItemComment;
import com.szxs.mapper.ImageMapper;
import com.szxs.mapper.ItemMapper;
import com.szxs.vo.ItemByMonthVo;
import com.szxs.vo.ItemDetailVo;
import com.szxs.vo.ItemLikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestItemController {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ImageMapper imageMapper;

    /**
     * 查询节目集合
     * @param item
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/queryItemList")
    public List<DmItem> queryItemList(DmItem item,
                                      @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                      @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(itemMapper.queryItemList(item));
        return pageInfo.getList();
    }

    /**
     * 查询可能喜欢
     * @param itemLikeDto
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/queryItemLike")
    public List<ItemLikeVo> queryItemLike(@RequestBody ItemLikeDto itemLikeDto,
                                          @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(itemMapper.queryItemLike(itemLikeDto));
        return pageInfo.getList();
    }


    @PostMapping("/queryItemListByBanner")
    public List<DmItem> queryItemListByBanner(DmItem item,
                                              @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                              @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo pageInfo = new PageInfo(itemMapper.queryItemListByBanner(item));
        return pageInfo.getList();
    }

    @PostMapping("/queryTodayRecommend")
    public List<DmItem> queryTodayRecommend(DmItem item,
                                            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(itemMapper.queryTodayRecommend(item));
        return pageInfo.getList();
    }

    @PostMapping("/queryDmImage")
    public DmImage queryDmImage(long id){
        return imageMapper.queryDmImage(id);
    }

    /**
     * 根据id查询剧场详情
     * @param itemDetailDto
     * @return
     */
    @PostMapping("/queryItemDetail")
    public ItemDetailVo queryItemDetail(@RequestBody ItemDetailDto itemDetailDto){
        return itemMapper.queryItemDetail(itemDetailDto);
    }

    /**
     * 2.	根据id查询剧场评论
     * @param commentDto
     * @return
     */
    @PostMapping("/queryItemComment")
    public List<DmItemComment> queryItemComment(@RequestBody CommentDto commentDto){
        return itemMapper.queryItemComment(commentDto);
    }

    @PostMapping("/queryItemHot")
    public List<DmItem> queryItemHot(@RequestBody ItemHotDto itemHotDto,
                                     @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(itemMapper.queryItemHot(itemHotDto));
        return pageInfo.getList();

    }

    /**
     * 根据月份查
     * @return
     */
    @PostMapping("/queryItemByMonth")
    public List<ItemByMonthVo> queryItemByMonth(@RequestBody ItemByMonthDto itemByMonthDto){
        return  itemMapper.queryItemByMonth(itemByMonthDto);
    }

    /**
     * 根据id查询itemName
     */
    @PostMapping("/queryItemNameById")
    public DmItem queryItemNameById(@RequestParam("id") Integer id){
        return itemMapper.queryItemNameById(id);
    }

}
