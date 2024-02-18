package com.xxy.xjsearchbacked.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface DataSource<T> {
    Page<T> doSearch(String searchText, long pageSize, long pageNum);
}
