package com.xxy.xjsearchbacked.service.impl;

import cn.hutool.json.JSONUtil;
import com.xxy.xjsearchbacked.model.entity.Picture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PictureService {
     public List<Picture> fetchPicture(String searchText) {
         List<Picture> pictureList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.bing.com/images/search?first=1&q=" + searchText).get();
            Elements elements = doc.getElementsByClass("imgpt");
            for (Element element : elements) {
                Picture picture = new Picture();
                Elements a = element.getElementsByTag("a");
                String m = a.attr("m");
                Map map = JSONUtil.toBean(m, Map.class);
                String murl = map.get("murl").toString();
                picture.setSrc(murl);
                pictureList.add(picture);
            }
//            System.out.println("抓取到：" + elements);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return pictureList;
    }

}
