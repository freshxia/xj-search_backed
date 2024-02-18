package com.xxy.xjsearchbacked.datasource;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxy.xjsearchbacked.model.entity.Picture;
import com.xxy.xjsearchbacked.model.entity.Post;
import com.xxy.xjsearchbacked.model.vo.PostVO;
import com.xxy.xjsearchbacked.service.PostService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDataSource implements DataSource<PostVO>{

    @Resource
    private PostService postService;
    @Override
    public Page<PostVO> doSearch(String searchText, long pageSize, long pageNum) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",searchText)
                .or().like("content",searchText)
                .or().like("tags",searchText);
        List<Post> postList = postService.list(queryWrapper);
        List<PostVO> voList = postList.stream()
                .map(post -> BeanUtil.copyProperties(post, PostVO.class))
                .collect(Collectors.toList());
        Page<PostVO> postPage = new Page<>(pageNum,pageSize);
        postPage.setRecords(voList);
        return postPage;
    }
}
