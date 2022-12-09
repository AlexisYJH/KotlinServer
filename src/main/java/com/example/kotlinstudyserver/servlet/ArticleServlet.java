package com.example.kotlinstudyserver.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "article", value = "/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    private static final String TAG ="ArticleServlet-";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/kotlinstudyserver/article?key=Jetpack
        System.out.println(TAG + "doGet");
        String key = request.getParameter("key");
        System.out.println(TAG + "key:" + key);

        List<Article> searchList = ServerStartupListener.getSearchList(key.trim());
        Gson gson = new Gson();
        JsonArray jsonArray = (JsonArray) gson.toJsonTree(searchList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
