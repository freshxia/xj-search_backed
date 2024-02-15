package com.xxy.xjsearchbacked.service;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

/**
 * 获取图片测试（使用jsoup 抓取 网页（bing）的图片）
 *
 * @author xiaxinyu
 * @date 2024/02/14
 */
@SpringBootTest
public class FetchPictureTest {
    @Test
    void fetchPicture () {
        try {
            Document doc = Jsoup.connect("https://www.bing.com/images/search?q=进击的巨人&first=1").get();
            Elements elements = doc.getElementsByClass("imgpt");
            for (Element element : elements) {
                Elements a = element.getElementsByTag("a");
                String m = a.attr("m");
                Map map = JSONUtil.toBean(m, Map.class);
                String murl = map.get("murl").toString();

                System.out.println(murl);
            }
//            System.out.println("抓取到：" + elements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
