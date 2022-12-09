package com.example.kotlinstudyserver.servlet;

import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "user", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    private static final String TAG ="UserServlet-";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/kotlinstudyserver/user?name=jack
        System.out.println(TAG + "doGet");
        String name = request.getParameter("name");
        System.out.println(TAG + "name:" + name);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("address", "China");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
