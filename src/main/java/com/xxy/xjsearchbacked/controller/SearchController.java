package com.xxy.xjsearchbacked.controller;

import com.xxy.xjsearchbacked.common.BaseResponse;
import com.xxy.xjsearchbacked.common.ErrorCode;
import com.xxy.xjsearchbacked.common.ResultUtils;
import com.xxy.xjsearchbacked.exception.BusinessException;
import com.xxy.xjsearchbacked.model.dto.search.SearchParamsRequest;
import com.xxy.xjsearchbacked.model.vo.ListResponse;
import com.xxy.xjsearchbacked.service.impl.SearchService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private SearchService searchService;
    @PostMapping("/vo")
    BaseResponse<ListResponse> doSearch(SearchParamsRequest searchParamsRequest) {
        if (searchParamsRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空！");
        }
        ListResponse resultList = searchService.deSearch(searchParamsRequest);
        return ResultUtils.success(resultList);
    }
}
