package com.example.kotlinstudyserver.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Utils {
    public static String loadJson(String path) {
        //路径

        BufferedReader reader = null;
        String result = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                //数据获取
                result += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;
        final int length = searchStr.length();
        if (length == 0) return true;
        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length)) return true;
        }
        return false;
    }
}
