package com.szxs.controller;

import com.szxs.dto.SearchItemListDto;
import com.szxs.service.SearchItemListService;
import com.szxs.util.DmwConstant;
import com.szxs.util.VoUtil;
import com.szxs.vo.RespDataVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/list")
@Log4j2
public class SearchItemListController {
    @Autowired
    private SearchItemListService searchItemListService;

    @PostMapping("/queryItemList")
    public RespDataVo queryItemList(@RequestBody SearchItemListDto searchItemListDto){
        try {
            RespDataVo respDataVo = VoUtil.getSuccess(searchItemListService.SearchItemListPage(searchItemListDto));
            respDataVo.setErrorCode(DmwConstant.SUCCESS_CODE);
            respDataVo.setSuccess("true");
            return respDataVo;
        }catch (Exception e){
            log.error(e.getMessage());
            return VoUtil.getFailed(DmwConstant.EXCEPTION_CODE,e.getMessage());
        }
    }
}
