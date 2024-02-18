package com.xxy.xjsearchbacked.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> {
    List<T> userList;
    List<T> postList;
    List<T> pictureList;
    List<?> dataList;
}
