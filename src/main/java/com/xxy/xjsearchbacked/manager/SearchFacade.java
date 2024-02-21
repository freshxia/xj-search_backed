package com.xxy.xjsearchbacked.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxy.xjsearchbacked.datasource.*;
import com.xxy.xjsearchbacked.model.dto.search.SearchParamsRequest;
import com.xxy.xjsearchbacked.model.entity.Picture;
import com.xxy.xjsearchbacked.model.entity.Post;
import com.xxy.xjsearchbacked.model.enums.SearchCategoryEnum;
import com.xxy.xjsearchbacked.model.vo.ListResponse;
import com.xxy.xjsearchbacked.model.vo.PostVO;
import com.xxy.xjsearchbacked.model.vo.UserVO;
import com.xxy.xjsearchbacked.service.PostService;
import com.xxy.xjsearchbacked.service.UserService;
import com.xxy.xjsearchbacked.service.impl.PictureService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SearchFacade {
    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public ListResponse deSearch(SearchParamsRequest searchParamsRequest) {
        ListResponse listResponse = new ListResponse();
//        从请求中 拿到搜索信息 和搜索分类
        String searchText = searchParamsRequest.getSearchText();
        String category = searchParamsRequest.getCategory();
        int current = searchParamsRequest.getCurrent();
        int pageSize = searchParamsRequest.getPageSize();
        // 如果搜索类型为空，则搜索全部类型
        if (StrUtil.isBlank(category)) {
            Page<Picture> picturePage = pictureDataSource.doSearch(searchText, pageSize, current);
            Page<PostVO> postVOPage = postDataSource.doSearch(searchText, pageSize, current);
            Page<UserVO> userVOPage = userDataSource.doSearch(searchText, pageSize, current);
            listResponse.setPictureList(picturePage.getRecords());
            listResponse.setUserList(userVOPage.getRecords());
            listResponse.setPostList(postVOPage.getRecords());
        } else {
            //否则根据传参搜索对应类型的数据 ：适配器模式
            DataSource dataSource = dataSourceRegistry.GetDataSourceByType(category);
            Page page = dataSource.doSearch(searchText, pageSize, current);
            listResponse.setDataList(page.getRecords());
        }
        return listResponse;
    }
}
