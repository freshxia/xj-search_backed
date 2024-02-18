package com.xxy.xjsearchbacked.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xxy.xjsearchbacked.common.ErrorCode;
import com.xxy.xjsearchbacked.exception.BusinessException;
import com.xxy.xjsearchbacked.model.dto.search.SearchParamsRequest;
import com.xxy.xjsearchbacked.model.entity.Picture;
import com.xxy.xjsearchbacked.model.enums.SearchCategoryEnum;
import com.xxy.xjsearchbacked.model.vo.ListResponse;
import com.xxy.xjsearchbacked.model.vo.PostVO;
import com.xxy.xjsearchbacked.model.vo.UserVO;
import com.xxy.xjsearchbacked.service.PostService;
import com.xxy.xjsearchbacked.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.xxy.xjsearchbacked.model.enums.SearchCategoryEnum.PICTURE;
import static com.xxy.xjsearchbacked.model.enums.SearchCategoryEnum.POST;

@Service
@Slf4j
public class SearchService {
    @Resource
    private PictureService pictureService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    public ListResponse deSearch(SearchParamsRequest searchParamsRequest) {
        ListResponse listResponse = new ListResponse();
//        从请求中 拿到搜索信息 和搜索分类
        String searchText = searchParamsRequest.getSearchText();
        String category = searchParamsRequest.getCategory();
        if (StrUtil.isBlank(category)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求的 category 参数 不能为空！");
            return listResponse;
        }
        switch (category) {
            case "picture" :
                List<Picture> pictureList = pictureService.fetchPicture(searchText);
                listResponse.setPictureList(pictureList);
                break;
            case "user" :
                List<UserVO> userVOList = userService.getUserVOBySearch(searchText);
                listResponse.setUserList(userVOList);
                break;
            case "post" :
                List<PostVO> postVOList = postService.getPostVOBySearch(searchText);
                listResponse.setPostList(postVOList);
            default:
                log.info("category 不符合要求");
        }
        return listResponse;
    }
}
