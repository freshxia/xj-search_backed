package com.xxy.xjsearchbacked.service;

import cn.hutool.core.lang.Console;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BaiduTranslateTest {
    @Test
    void testTranslate() {
        String url = "https://fanyi-api.baidu.com/api/trans/vip/translate";
        String q = "猪肉炖粉条";
        String from = "zh";
        String to = "en";
        String appid = "20231024001858098";
        String salt = "1435660288";
        String secretKey = "nk9CtKvwjaJb_1jLMfs9";
//        appid+q+salt+密钥的MD5值
        String sign = SecureUtil.md5(appid + q + salt + secretKey);
//        q=apple
//        from=en
//        to=zh
//        appid=2015063000000001（请替换为您的appid）
//        salt=1435660288（随机码）
//        平台分配的密钥: 12345678
        Map paramMap = new HashMap<>();
        paramMap.put("q",q);
        paramMap.put("from",from);
        paramMap.put("to",to);
        paramMap.put("appid",appid);
        paramMap.put("salt",salt);
        paramMap.put("sign",sign);
        //链式构建请求
        String result2 = HttpRequest.post(url)
//                .header("Accept-Language","zh-CN")//头信息，多个头信息多次调用此方法即可
                .contentType("application/x-www-form-urlencoded")
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        String s = UnicodeUtil.toString(result2);
        Console.log(s);

    }
}
