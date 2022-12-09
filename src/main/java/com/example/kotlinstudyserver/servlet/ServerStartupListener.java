package com.example.kotlinstudyserver.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerStartupListener implements ServletContextListener{
    private static final String TAG ="ServerStartupListener-";
    private static final List<Article> ARTICLES = new ArrayList<>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //构建一个内存数据集合
        URL url = getClass().getClassLoader().getResource("data.json");
        File file = new File(url.getFile());
        String json = Utils.loadJson(file.getAbsolutePath());
        System.out.println(TAG + json);
        JsonObject rootElement = (JsonObject) new JsonParser().parse(json);
        JsonArray subjects = rootElement.get("subjects").getAsJsonArray();
        for (int i = 0; i < subjects.size(); i++) {
            JsonObject element = (JsonObject) subjects.get(i);
            Article Article = new Article();
            Article.setId(element.get("id").getAsInt());
            Article.setText(element.get("text").getAsString());
            ARTICLES.add(Article);
        }
        System.out.println(TAG + ARTICLES.size());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static List<Article> getSearchList(String key) {
        List<Article> searchList = new ArrayList<>();
        if (key != null && !key.isEmpty()) {
            int size = ARTICLES.size();
            for (int i = 0; i < size; i++) {
                if (Utils.containsIgnoreCase(ARTICLES.get(i).getText(), key)) {
                    searchList.add(ARTICLES.get(i));
                }
            }
        }
        System.out.println(TAG + "getSearchList:" + searchList.size());
        return searchList;
    }


}
