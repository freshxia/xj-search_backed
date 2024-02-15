package com.xxy.xjsearchbacked.service;

import cn.hutool.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取文章
 *
 * @author xiaxinyu
 * @date 2024/02/15
 */
@SpringBootTest
public class FetchPostTest {
    @Test
    void doFetchPost() {
        String category = "文章";
        int current= 1;
        int pageSize= 8;
        int reviewStatus= 1;
        String  searchText= "";
        String  sortField= "_score";
        String sortOrder = "descend";
        String cookie = "SESSION=YzMzOTNlOWYtM2ViMC00MjJlLTgyZTItNTUxY2RiZjgwNjc5; __51vcke__JtwgXxXhywMwFmhF=3e9f3e8f-08f6-502d-8baa-9df4f088837a; __51vuft__JtwgXxXhywMwFmhF=1690541560794; __51huid__JyijP8zIhGUD9hWn=230c7d23-f352-57ae-b467-ef0726f65801; __51uvsct__JtwgXxXhywMwFmhF=12; Hm_lvt_3a44649edfc3f514e794de38320045f0=1707105478,1707555836,1707921370; __vtins__JtwgXxXhywMwFmhF=%7B%22sid%22%3A%20%2238f257e6-4f9f-5cdf-a9b9-d017846475ac%22%2C%20%22vd%22%3A%202%2C%20%22stt%22%3A%205122%2C%20%22dr%22%3A%205122%2C%20%22expires%22%3A%201707923174749%2C%20%22ct%22%3A%201707921374749%7D; Hm_lpvt_3a44649edfc3f514e794de38320045f0=1707921375";
        Map params = new HashMap<>();
        params.put("category",category);
        params.put("current",current);
        params.put("pageSize",pageSize);
        params.put("reviewStatus",reviewStatus);
        params.put("searchText",searchText);
        params.put("sortField",sortField);
        params.put("sortOrder",sortOrder);
        String res = HttpRequest.post("https://www.code-nav.cn/api/post/search/page/vo")
                .contentType("application/json")
                .cookie(cookie)
                .form(params)
                .execute()
                .body();
        System.out.println("请求结果："+res);
    }
}
