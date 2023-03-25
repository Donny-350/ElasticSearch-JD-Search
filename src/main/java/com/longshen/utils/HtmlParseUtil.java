package com.longshen.utils;

import com.longshen.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
    }

    public List<Content> parseJD(String keywords) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        // 解析网页 (Jsoup返回Document就是Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");

        List<Content> goodsList = new ArrayList<>();

        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setTitle(title);
            content.setPrice(price);
            content.setImg(img);
            goodsList.add(content);
        }
        return goodsList;
    }
}
