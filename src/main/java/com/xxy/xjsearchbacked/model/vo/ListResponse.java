package com.xxy.xjsearchbacked.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> {
    List<T> userList;
    List<T> postList;
    List<T> pictureList;
}
