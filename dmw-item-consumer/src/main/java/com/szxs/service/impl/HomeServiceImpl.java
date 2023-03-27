package com.szxs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.szxs.dto.*;
import com.szxs.entity.*;
import com.szxs.feign.ItemTypeClient;
import com.szxs.mapper.ImageMapper;
import com.szxs.service.HomeService;
import com.szxs.util.DmwConstant;
import com.szxs.util.EmptyUtil;
import com.szxs.vo.*;
import com.szxs.util.AlxConstant;
import com.szxs.util.EmptyUtil;
import com.szxs.util.RedisUtil;
import com.szxs.vo.FloorItemVo;
import com.szxs.vo.ItemTypeVo;
import com.szxs.vo.ItemVo;
import com.szxs.vo.TransverseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ItemTypeClient itemTypeClient;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 1.	查询所有商品分类
     *
     * @return
     */
    public List<ItemTypeVo> queryAllType() {
        //生成一个键：queryAllType:
        String key = "queryAllType";
        //先从REDIS查询
        String itemTypeVoListStr = redisUtil.get(key);

        //查询第一级目录
        List<DmItemType> itemTypeList = itemTypeClient.queryItemTypeList(new DmItemType("1"));
        List<ItemTypeVo> itemTypeVoList = convertDmItemTypeToItemTypeVo(itemTypeList);
        DmImage dmImage = null;

        for (ItemTypeVo itemTypeVo : itemTypeVoList) {
            DmItemType itemType = new DmItemType();
            itemType.setParent(itemTypeVo.getId());
            itemTypeVo.setChildren(convertDmItemTypeToItemTypeVo(itemTypeClient.queryItemTypeList(itemType)));
            DmItem dmItem = new DmItem();
            dmItem.setItemType1Id(itemTypeVo.getId());
            itemTypeVo.setHotItems(convertDmItemToItemVo(itemTypeClient.queryItemList(dmItem,1,4)));


            for (ItemVo itemVo : itemTypeVo.getHotItems()){
                //先从redis查  img:category(0或者1):type:dm_item.id 没有再到数据库查    都没有就给默认

                itemVo.setImgUrl("http://localhost/calendar03.png");
            }
        }
        if (itemTypeVoList != null) {
            redisUtil.set(key, JSON.toJSONString(itemTypeList));
        } else if (EmptyUtil.isNotEmpty(itemTypeVoListStr)) {
            //如果不为空就把字符串转成集合返回出去
            itemTypeVoList = JSON.parseArray(itemTypeVoListStr, ItemTypeVo.class);
        }
        return itemTypeVoList;
    }

    /**
     * 2.查询一级分类
     *
     * @return
     */
    public List<TransverseVo> queryTransverse() {
        DmItemType dmItemType = new DmItemType();
        dmItemType.setLevel("1");
        String key = "queryTransverse";
        String itemListStr = redisUtil.get(key);
        List<DmItemType> dmItemTypeList = new ArrayList<DmItemType>();
        if (EmptyUtil.isEmpty(itemListStr)) {
            dmItemTypeList = itemTypeClient.queryItemTypeList(dmItemType);
            redisUtil.set(key, JSON.toJSONString(dmItemTypeList));
        } else if (EmptyUtil.isNotEmpty(itemListStr)) {
            dmItemTypeList = JSON.parseArray(itemListStr, DmItemType.class);
        }
        return converDmItemToTransverseVo(dmItemTypeList);
    }

    /**
     * 3.	轮播图
     *
     * @return
     */
    public List<ItemVo> queryBanner() {
        DmItem dmItem = new DmItem();
        //先从REDIS查询
        String key = "queryBanner";
        String itemListStr = redisUtil.get(key);
        List<DmItem> dmItems = new ArrayList<DmItem>();
        if (EmptyUtil.isEmpty(itemListStr)) {
            dmItems = itemTypeClient.queryItemListByBanner(dmItem, 1, 5);
            redisUtil.set(key, JSON.toJSONString(dmItems));

        } else if (EmptyUtil.isNotEmpty(itemListStr)) {
            dmItems = JSON.parseArray(itemListStr, DmItem.class);
        }
        if (EmptyUtil.isNotEmpty(redisUtil.get(key))) {
            int i = 1;
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/pic" + i++ + ".jpg");
            }
        } else {
            int i = 1;
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/pic" + i++ + ".jpg");
            }
        }
        return convertDmItemToItemVo(dmItems);
    }

    /**
     * 4.	今日推荐
     *
     * @return
     */
    public List<ItemVo> queryTodayRecommend() {
        DmItem dmItem = new DmItem();
        String key = "queryTodayRecommend";
        String itemListStr = redisUtil.get(key);
        List<DmItem> dmItems = new ArrayList<DmItem>();
        if (EmptyUtil.isEmpty(itemListStr)) {
            dmItems = itemTypeClient.queryTodayRecommend(dmItem, 1, 5);
            redisUtil.set(key, JSON.toJSONString(dmItems));

        } else if (EmptyUtil.isNotEmpty(itemListStr)) {
            dmItems = JSON.parseArray(itemListStr, DmItem.class);
        }
        if (EmptyUtil.isNotEmpty(redisUtil.get(key))) {
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/cardimg8.jpg");
            }
        }
        return convertDmItemToItemVo(dmItems);
    }

    /**
     * 5.	即将开售
     *
     * @return
     */
    public List<ItemVo> queryToSaleItem() {
        return null;
    }

    /**
     * 6.	剧场类型推荐电影（1F/2F。。。）
     *
     * @return
     */
    public List<FloorVo> queryFloorItems() {
        String key = "queryFloorItems";
        String itemListStr = redisUtil.get(key);

        List<FloorVo> floorVoList1 = itemTypeClient.queryFloorList(new FloorVo());
        for (FloorVo floorVo : floorVoList1) {
            DmItem dmItem = new DmItem();
            dmItem.setItemType1Id(floorVo.getItemTypeId());
            dmItem.setIsBanner(floorVo.getFloor());
            List<DmItem> dmItems = itemTypeClient.queryFloorItems(dmItem, 1, 5);
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/cardimg8.jpg");
            }
            floorVo.setItems(convertDmItemToFloorItemVo(dmItems));
        }

        if (floorVoList1 != null) {
            redisUtil.set(key, JSON.toJSONString(floorVoList1));
        } else if (EmptyUtil.isNotEmpty(floorVoList1)) {
            //如果不为空就把字符串转成集合返回出去
            floorVoList1 = JSON.parseArray(itemListStr, FloorVo.class);
        }
        return floorVoList1;
    }
    /**
     * 7.	热门演出推荐排行
     * @return
     */
    public List<ItemVo> queryHotItems(ItemTypeIdDto itemTypeIdDto) {
        DmItem dmItem = new DmItem();
        String key = "queryHotItems";
        String itemListStr = redisUtil.get(key);
        List<DmItem> dmItems = new ArrayList<DmItem>();


        dmItem.setItemType1Id(itemTypeIdDto.getItemTypeId());


        if (EmptyUtil.isEmpty(itemListStr)) {
            dmItems = itemTypeClient.queryFloorItems(dmItem, 1, 10);
            redisUtil.set(key, JSON.toJSONString(dmItems));

        } else if (EmptyUtil.isNotEmpty(itemListStr)) {
            dmItems = JSON.parseArray(itemListStr, DmItem.class);
        }

        if (EmptyUtil.isNotEmpty(redisUtil.get(key))) {
            int i = 1;
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/pic" + i++ + ".jpg");
            }
        } else {
            int i = 1;
            for (DmItem item : dmItems) {
                item.setImgUrl("http://localhost/pic" + i++ + ".jpg");
            }
        }
        return convertDmItemToItemVo(dmItems);
    }

    /**
     * 1.音乐会专区
     * @param itemByAgeDto
     * @return
     */
    public List<FloorItemVo> queryItemByAge(ItemByAgeDto itemByAgeDto) {
        String key = "queryItemByAge";
        String itemListStr = redisUtil.get(key);

        DmItem dmItem = new DmItem();
        dmItem.setAgeGroup(itemByAgeDto.getAgeGroup());
        List<DmItem> dmItems = itemTypeClient.queryFloorItems(dmItem, 1, itemByAgeDto.getLimit());
        for (DmItem item : dmItems) {
            item.setImgUrl("http://localhost/cardimg8.jpg");
        }

        if (dmItems != null) {
            redisUtil.set(key, JSON.toJSONString(dmItems));
        } else if (EmptyUtil.isNotEmpty(dmItems)) {
            //如果不为空就把字符串转成集合返回出去
            dmItems = JSON.parseArray(itemListStr, DmItem.class);
        }
        return convertDmItemToFloorItemVo(dmItems);
    }

    /**
     * 2.	精彩聚集(2)
     * @param advertisingDto
     * @return
     */
    public List<FloorItemVo> queryAdvertising(AdvertisingDto advertisingDto) {
        DmItem dmItem = new DmItem();
        dmItem.setItemType1Id(advertisingDto.getItemTypeId());
        List<DmItem> dmItems = itemTypeClient.queryFloorItems(dmItem, 1, advertisingDto.getLimit());
        for (DmItem dmItem1 : dmItems) {
            dmItem1.setImgUrl("http://localhost/cardimg8.jpg");
        }
        return convertDmItemToFloorItemVo(dmItems);
    }

    /**
     * 3.	轮播图
     * @param itemTypeIdDto
     * @return
     */
    public List<ItemVo> querySlideShowPic(ItemTypeIdDto itemTypeIdDto) {
        DmItem dmItem = new DmItem();
        dmItem.setItemType1Id(itemTypeIdDto.getItemTypeId());
        dmItem.setIsRecommend(AlxConstant.SQL_FINAL.IS);
        List<DmItem> dmItems = itemTypeClient.queryFloorItems(dmItem, 1, 5);
        int i = 1;
        for (DmItem item : dmItems) {
            item.setImgUrl("http://localhost/pic" + i++ + ".jpg");
        }
        return convertDmItemToItemVo(dmItems);
    }

    public List<ItemCommentVo> queryItemComment(CommentDto commentDto) {
        if (EmptyUtil.isNotEmpty(commentDto)) {
            commentDto.setItemId(commentDto.getId());
            List<DmItemComment> dmItemComments = itemTypeClient.queryItemComment(commentDto);
            String jsonString = JSON.toJSONString(dmItemComments);
            return JSON.parseArray(jsonString, ItemCommentVo.class);
        }
        return null;
    }

    public List<DmSchedulerVo> queryScheduler(DmScheduler dmScheduler) {
        List<DmSchedulerVo> dmSchedulerVos = itemTypeClient.queryScheduler(dmScheduler);
        return dmSchedulerVos;
    }

    public List<SchedulerSeatPriceVo> queryPrice(DmSchedulerSeatPrice dmSchedulerSeatPrice) {
        //SchedulerSeatPriceVo schedulerSeatPriceVo = new SchedulerSeatPriceVo();
        List<SchedulerSeatPriceVo> queryPrice = itemTypeClient.queryPrice(dmSchedulerSeatPrice);
        for (SchedulerSeatPriceVo ss : queryPrice) {
            ss.setIsHaveSeat(1);
        }
        return queryPrice;
    }

    /**
     * 精彩聚集
     * @param itemLikeDto
     * @return
     */
    public List<FloorItemVo> queryItemNice(ItemLikeDto itemLikeDto) {
        itemLikeDto.setItemType1Id(itemLikeDto.getItemTypeId());
        List<ItemLikeVo> itemLikeVos = itemTypeClient.queryItemLike(itemLikeDto,1,itemLikeDto.getLimit());
        String jsonString = JSON.toJSONString(itemLikeVos);
        return JSON.parseArray(jsonString, FloorItemVo.class);
    }

    public List<FloorItemVo> queryItemHot(ItemHotDto itemHotDto) {
        itemHotDto.setItemType1Id(itemHotDto.getItemTypeId());
        List<DmItem> dmItems = itemTypeClient.queryItemHot(itemHotDto, 1, itemHotDto.getLimit());
        String jsonString = JSON.toJSONString(dmItems);
        return JSON.parseArray(jsonString, FloorItemVo.class);
    }

    public List<ItemByMonthVo> queryItemByMonth(ItemByMonthDto itemByMonthDto) {

        if (EmptyUtil.isNotEmpty(itemByMonthDto)) {
            List<ItemByMonthVo> itemByMonthVos = itemTypeClient.queryItemByMonth(itemByMonthDto);
            for (ItemByMonthVo itemByMonthVo : itemByMonthVos){
                itemByMonthVo.setDay(itemByMonthVo.getStartDate());
                itemByMonthVo.setImgUrl("http://localhost/cardimg8.jpg");
            }
            return itemByMonthVos;
        }
        return null;
    }


    /**
     * redis获取图片数据
     * @param itemTypeVo
     */
    private void SetImgUrlByItemType(ItemTypeVo itemTypeVo) {
        DmImage dmImage;
        for (ItemVo itemVo : itemTypeVo.getHotItems()) {
            dmImage = new DmImage();

            dmImage.setId(itemVo.getId());

            Long id = dmImage.getId();
//                System.out.println(id);

            DmImage dmImage1 = itemTypeClient.queryDmImage(id);
            //先从redis查  img:category(0或者1):type:dm_item.id 没有再到数据库查    都没有就给默认
            String s = redisUtil.get(AlxConstant.User.IMG_PREFIX + dmImage1.getCategory() + AlxConstant.User.IMG_TYPE_POSTFIX + itemVo.getId());
//                System.out.println(s);
            if (EmptyUtil.isEmpty(s)){
                if (EmptyUtil.isNotEmpty(dmImage1.getImgUrl())){
                    itemVo.setImgUrl(dmImage1.getImgUrl());
                }else {
                    //都没有的情况下
                    redisUtil.set(AlxConstant.User.IMG_PREFIX + dmImage1.getCategory() + AlxConstant.User.IMG_TYPE_POSTFIX + itemVo.getId(),"ssss",AlxConstant.User.IMG_EXPIRE_TIME);
                }
            }else {
                //从redis取值
                String s1 = redisUtil.get(AlxConstant.User.IMG_PREFIX + dmImage1.getCategory() + AlxConstant.User.IMG_TYPE_POSTFIX + itemVo.getId());
                itemVo.setImgUrl("http://localhost/cardimg8.jpg");
//                    System.out.println(itemVo.getImgUrl());
            }
        }
    }


    public List<ItemTypeVo> queryItemType(SearchItemTypeDto searchItemTypeDto) {

        if (EmptyUtil.isNotEmpty(searchItemTypeDto)) {
            List<ItemTypeVo> itemTypeVoList = itemTypeClient.queryItemType(searchItemTypeDto);
            String jsonString = JSON.toJSONString(itemTypeVoList);
            List<ItemTypeVo> typeVoList = JSON.parseArray(jsonString, ItemTypeVo.class);
            return typeVoList;
        }
        return null;
    }

    /**
     * 查询猜你喜欢
     * @param itemLikeDto
     * @return
     */
    public List<ItemLikeVo> queryItemLike(ItemLikeDto itemLikeDto) {
        ItemLikeVo itemLikeVo = new ItemLikeVo();

        //itemLikeVo.setImgUrl("http://localhost/cardimg8.jpg");
        if (EmptyUtil.isNotEmpty(itemLikeDto)){
            if (itemLikeDto.getItemTypeId() > 9){
                itemLikeDto.setItemType2Id(itemLikeDto.getItemTypeId());
                itemLikeDto.setItemType1Id(0);
            }else if (itemLikeDto.getItemTypeId() < 9){
                itemLikeDto.setItemType1Id(itemLikeDto.getItemTypeId());
                itemLikeDto.setItemType2Id(0);
            }
            List<ItemLikeVo> likeVoList = itemTypeClient.queryItemLike(itemLikeDto,1,6);
            for (ItemLikeVo itemLikeVo2 : likeVoList){
                itemLikeVo2.setImgUrl("http://localhost/cardimg8.jpg");
            }
            String jsonString = JSON.toJSONString(likeVoList);
            List<ItemLikeVo> itemLikeVoList = JSON.parseArray(jsonString, ItemLikeVo.class);
            return itemLikeVoList;
        }
        return null;
    }

    /**
     * 根据id查询剧场详情
     * @param itemDetailDto
     * @return
     */
    public ItemDetailVo queryItemDetail(ItemDetailDto itemDetailDto) {

        if (EmptyUtil.isNotEmpty(itemDetailDto)) {
            ItemDetailVo itemDetailVo = itemTypeClient.queryItemDetail(itemDetailDto);

            if (EmptyUtil.isEmpty(itemDetailVo.getImgUrl())) {
                //itemDetailVo.setImgUrl(DmwConstant.IMG_DEFAULT_URL);
                itemDetailVo.setImgUrl("http://localhost/cardimg8.jpg");
            }
            return itemDetailVo;
        }
        return null;
    }



    private List<ItemTypeVo> convertDmItemTypeToItemTypeVo(List<DmItemType> itemTypeList){
        List<ItemTypeVo> itemTypeVoList = new ArrayList<ItemTypeVo>();

        ItemTypeVo itemTypeVo = null;
        for (DmItemType itemType : itemTypeList) {
            itemTypeVo = new ItemTypeVo();
            BeanUtils.copyProperties(itemType, itemTypeVo);
            itemTypeVoList.add(itemTypeVo);
        }
        return itemTypeVoList;
    }

    private List<FloorItemVo> convertDmItemToFloorItemVo(List<DmItem> dmItems) {
        String jsonString = JSON.toJSONString(dmItems);
        return JSON.parseArray(jsonString, FloorItemVo.class);
    }

    private List<ItemVo> convertDmItemToItemVo(List<DmItem> itemList) {
        String jsonString = JSON.toJSONString(itemList);
        return JSON.parseArray(jsonString, ItemVo.class);
    }

    private List<TransverseVo> converDmItemToTransverseVo(List<DmItemType> itemTypeList) {
        String jsonString = JSON.toJSONString(itemTypeList);
        return JSON.parseArray(jsonString, TransverseVo.class);
    }

}
