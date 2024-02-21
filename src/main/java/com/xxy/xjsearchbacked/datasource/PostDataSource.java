package com.xxy.xjsearchbacked.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxy.xjsearchbacked.model.dto.post.PostQueryRequest;
import com.xxy.xjsearchbacked.model.entity.Post;
import com.xxy.xjsearchbacked.model.vo.PostVO;
import com.xxy.xjsearchbacked.model.vo.UserVO;
import com.xxy.xjsearchbacked.service.PostService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class PostDataSource implements DataSource<PostVO>{

    @Resource
    private PostService postService;
    @Override
    public Page<PostVO> doSearch(String searchText, long pageSize, long pageNum) {
//
//        List<PostVO> postVOBySearch = postService.getPostVOBySearch(searchText);
//
//        Page<PostVO> postVOPage = new Page<>(pageNum, pageSize);
//        postVOPage.setRecords(postVOBySearch);
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent((int) pageNum);
        postQueryRequest.setPageSize((int) pageSize);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Page<PostVO> postVOPage = postService.getPostVOPage(postPage, request);

        return postVOPage;
    }
}
