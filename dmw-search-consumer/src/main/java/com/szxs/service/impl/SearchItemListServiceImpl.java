package com.szxs.service.impl;

import com.szxs.dto.SearchItemListDto;
import com.szxs.entity.Page;
import com.szxs.feign.SearchClient;
import com.szxs.service.SearchItemListService;
import com.szxs.util.DateUtil;
import com.szxs.util.DmwConstant;
import com.szxs.util.EmptyUtil;
import com.szxs.util.RedisUtil;
import com.szxs.vo.SearchItemListVo;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class SearchItemListServiceImpl implements SearchItemListService {
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private RedisUtil redisUtil;

    public Page<SearchItemListVo> SearchItemListPage(SearchItemListDto searchItemListDto) {

        String readTime = redisUtil.get(DmwConstant.SEARCH_READTIME);
        String newTime = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        Page<SearchItemListVo> page = new Page<SearchItemListVo>();

        //设置当前页 页面大小 起始页
        if (EmptyUtil.isNotEmpty(searchItemListDto)) {
            page.setCurPage(searchItemListDto.getCurrentPage());
            page.setPageSize(searchItemListDto.getPageSize());
            page.setBeginPos((searchItemListDto.getCurrentPage() - 1) * searchItemListDto.getPageSize());

            if(EmptyUtil.isEmpty(readTime)){
                redisUtil.set(DmwConstant.SEARCH_READTIME, newTime);
            }
            String esreadTime = redisUtil.get(DmwConstant.SEARCH_READTIME);
            List<SearchItemListVo> searchItemListVoList = searchClient.querySearchItemList(esreadTime);
            if (EmptyUtil.isNotEmpty(searchItemListVoList)) {
                //设置全文分词
                for (SearchItemListVo searchItemListVo : searchItemListVoList) {
                    searchItemListVo.getKeyword().add(searchItemListVo.getItemName());
                    searchItemListVo.getKeyword().add(searchItemListVo.getAddress());
                    searchItemListVo.getKeyword().add(searchItemListVo.getAbstractMessage());
                }
                elasticsearchRestTemplate.save(searchItemListVoList);
                redisUtil.set(DmwConstant.SEARCH_READTIME, newTime);
            }

            //查询数据
            //创建布尔语句 组合查询
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (EmptyUtil.isNotEmpty(searchItemListDto.getKeyword())) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("keyword", searchItemListDto.getKeyword()));
            }
            if (EmptyUtil.isNotEmpty(searchItemListDto.getAreaId()) && searchItemListDto.getAreaId() > 0) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("areaId", searchItemListDto.getAreaId()));
            }
            if (EmptyUtil.isNotEmpty(searchItemListDto.getItemTypeId1()) && searchItemListDto.getItemTypeId1() > 0) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("itemTypeId1", searchItemListDto.getItemTypeId1()));
            }
            if (EmptyUtil.isNotEmpty(searchItemListDto.getItemTypeId2()) && searchItemListDto.getItemTypeId2() > 0) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("itemTypeId2", searchItemListDto.getItemTypeId2()));
            }
            if(EmptyUtil.isNotEmpty(searchItemListDto.getStartTime())){
                boolQueryBuilder.must(QueryBuilders.rangeQuery("startTimeLong").gte((
                        Date.from(searchItemListDto.getStartTime().atStartOfDay(ZoneId.systemDefault()).toInstant())).getTime()));
            }
            if(EmptyUtil.isNotEmpty(searchItemListDto.getEndTime())){
                boolQueryBuilder.must(QueryBuilders.rangeQuery("endTimeLong").lte((
                        Date.from(searchItemListDto.getEndTime().atStartOfDay(ZoneId.systemDefault()).toInstant())).getTime()));
            }


            //设置查询对象分布和排序
            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
            queryBuilder.withQuery(boolQueryBuilder);

            //设置分页
            queryBuilder.withPageable(PageRequest.of(page.getBeginPos(), page.getPageSize()));

            //查询对象创建
            try{
                NativeSearchQuery searchQuery = queryBuilder.build();

                //执行查询，设置查询条件和返回的对象
                SearchHits<SearchItemListVo> search = elasticsearchRestTemplate.search(searchQuery, SearchItemListVo.class);
                List<SearchItemListVo> searchItemListVos = new ArrayList<>();
                for (SearchHit<SearchItemListVo> s : search.getSearchHits()) {
                    SearchItemListVo searchItemListVo = s.getContent();
                    searchItemListVo.setCreatedTime(new Date(searchItemListVo.getCreatedTimeLong()).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime());
                    searchItemListVo.setEndTime(new Date(searchItemListVo.getEndTimeLong()).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime());
                    searchItemListVo.setStartTime(new Date(searchItemListVo.getStartTimeLong()).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime());
                    searchItemListVo.setImgUrl("http://localhost/cardimg8.jpg");
                    searchItemListVos.add(searchItemListVo);
                }

                //设置vo数据
                page.setRows(searchItemListVos);
                //设置总行数
                page.setTotal(((Long) search.getTotalHits()).intValue());
                //设置总页数
                page.setPageCount((page.getTotal() + page.getPageSize() - 1) / page.getPageSize());
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return page;
    }

}
