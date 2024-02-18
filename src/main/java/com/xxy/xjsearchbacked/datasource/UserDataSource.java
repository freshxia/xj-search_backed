package com.xxy.xjsearchbacked.datasource;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxy.xjsearchbacked.model.entity.User;
import com.xxy.xjsearchbacked.model.vo.UserVO;
import com.xxy.xjsearchbacked.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataSource implements DataSource{
    @Resource
    private UserService userService;
    @Override
    public Page<UserVO> doSearch(String searchText, long pageSize, long pageNum) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",searchText);
        List<User> users = userService.list(queryWrapper);
        List<UserVO> voList = users.stream().map(user -> BeanUtil.copyProperties(user, UserVO.class))
                .collect(Collectors.toList());
        Page<UserVO> userVOPage = new Page<>(pageNum, pageSize);
        userVOPage.setRecords(voList);
        return userVOPage;
    }
}
