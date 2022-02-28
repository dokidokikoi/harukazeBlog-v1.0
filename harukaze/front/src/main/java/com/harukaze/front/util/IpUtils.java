package com.harukaze.front.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class IpUtils {
    /*
    * 使用nginx等反向代理软件，则不能通过request.getRemoteAddr()获取IP地址
    * */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null, unknown = "unknown", seperator = ",";
        int maxLength = 15;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP-CLIENT_IP");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_x_FORWARDED_FOR");
            }
            if (StrUtil.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IpUtils ERROR ", e);
        }

        //使用代理，则获取第一个IP地址
        if (StrUtil.isBlank(ip) && ip.length() > maxLength) {
            int idx = ip.indexOf(seperator);
            if (idx > 0) {
                ip = ip.substring(0, idx);
            }
        }
        return ip;
    }

    /**
     * 发送get 请求，比如定时任务调用的就是请求
     * @param url
     * @return
     */
    public static JSONObject sendGet2(String url) {
        JSONObject jsonObject = null;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlName = url;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(10000);
            // 建立实际的连接
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            jsonObject = JSONUtil.parseObj(sb.toString());
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            // 使用finally块来关闭输入流
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("关闭流异常");
            }
        }
        return jsonObject;
    }

    public static String getIpAddress(String ip) {
        if (StrUtil.isBlank(ip)) {
            return "";
        }

//        String url = "http://api.map.baidu.com/location/ip?&ak=XLOp9rLuNN4kAi6790zP02MT0xlw2uWm";
//        if (!"0:0:0:0:0:0:0:1".equals(ip)) {
//            url += "&ip="+ip;
//        }
//        JSONObject jsonObject = IpUtils.sendGet2(url);
//        System.out.println(jsonObject);
//        //获取content中的值
//        JSONObject contentJsonObject = jsonObject.getJSONObject("content");
//        System.out.println("contentJsonObject-------"+contentJsonObject);
//        //获取详细地址数据
//        JSONObject addressDetailJsonObject = contentJsonObject.getJSONObject("address_detail");
//        String address = addressDetailJsonObject.get("province",String.class);
//        String city = addressDetailJsonObject.get("city",String.class);
//        System.out.println("address_detail-------"+addressDetailJsonObject);
//        System.out.println("address-------"+address);
//        System.out.println("city-------"+city);
//
//        String address2 = contentJsonObject.get("address",String.class);
//        System.out.println("address2-----"+address2);
//
//        JSONObject pointJsonObject = contentJsonObject.getJSONObject("point");
//        String x = pointJsonObject.get("x",String.class);
//        String y = pointJsonObject.get("y",String.class);
//        System.out.println("x----"+x);
//        System.out.println("y----"+y);
//        return address+","+city;
        return null;
    }
}


















