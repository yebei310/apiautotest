package com.course.cases.group;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;


/**
 * 退单
 */

public class ReturnOrder {

    //设置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();
    //1、退单列表展示
    @Test(description = "退单列表")
    public void returnList() {
        //CloseableHttpClient：建立一个可以关闭的httpClient
        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-operation/after-sale-mgr/order/return/list");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("cookie", "X-SSPP-UID=Cm8KLly1T2VLgTLMA4QnAg==; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%2216a61f389935b4-0b2eb777907c4b-366d7e04-1296000-16a61f3899481a%22%7D; _ga=GA1.2.974017705.1561297447; qa4_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTM2MzksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDM2Mzk0MjAsXCJleHBcIjoxNTY0ODg1NjM5NDIwLFwiaWF0XCI6MTU2MjI5MzYzOTQyMCxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiYjc0NzA3ZDMtMWM2Ni00YTg5LTllYTktYzEwMjQ2M2UwOTg0XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODU2Mzl9.7y2_X_4U-qm-56Ee4IwAC5uBaOjlmTxN4NOAr2sbAZs; username=yebeibei; qa7_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTc5ODgsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDc5ODg4NDMsXCJleHBcIjoxNTY0ODg5OTg4ODQzLFwiaWF0XCI6MTU2MjI5Nzk4ODg0MyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiNThlZGU3ZjQtMTM5ZC00ZjkwLWI1ODMtMjljNGFlNGYyNjQ1XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODk5ODh9.mlv_M7Rdo_aJ-Ekg5kw_XZsYxmP02_pZF5KnKDCp1pI; sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjYzNDUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzYzNDU5NjMsXCJleHBcIjoxNTY0OTE4MzQ1OTYzLFwiaWF0XCI6MTU2MjMyNjM0NTk2MyxcImlwXCI6XCIyMTguMjQxLjEzMC4yMTBcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiY2NmYjdmY2MtOGY5Ny00Y2E0LWI2NjctOGU5NzcxOWVmNTdiXCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ5MTgzNDV9.DTxBafWQk31cgzyh10PWi9LjdWzH1D1PGg48zRPGJFU; qa9_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjcxODksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzcxODk1NjcsXCJleHBcIjoxNTY0OTE5MTg5NTY3LFwiaWF0XCI6MTU2MjMyNzE4OTU2NyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiOTEzNjE4YzgtY2VhNC00OGFmLTg5OGItZTkwODZiNDc3ZTY4XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6MjY3MyxcInVzZXJOYW1lXCI6XCJ5ZWJlaWJlaVwifSIsImV4cCI6MTU2NDkxOTE4OX0.exR5oX6OJGfxDHZCXwlwxNYKBZjQftUCnFQmKTru4ic");
            //发送的参数数据{"returnOrderStatus":"1","pageSize":"20","pageNum":"1","needCount":true}
            Map<String, String> map = new HashMap<String, String>();
            map.put("returnOrderStatus", "1");
            map.put("pageSize", "20");
            map.put("pageNum", "1");
            map.put("needCount", "true");
            //设置发送的数据
            StringEntity s = new StringEntity(JSON.toJSONString(map));
            s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            //获取返回值
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                JSONObject jsonObject = JSONObject.parseObject(result)  ;
                String headresult = jsonObject.get("head").toString();
                JSONObject jsonObjecthead = JSONObject.parseObject(headresult);
               String aa =jsonObjecthead.get("message").toString();
               if (aa.contains("未授权")) {
                   System.out.println("没有权限");
                   Assert.assertTrue(false)  ;
               }


//                jsonArray.getJSONObject(0);
                System.out.println("POST请求返回的数据是：" + result);
            }
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {     //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test(description = "退单列表页状态")
    public void returnStatus() {
        //CloseableHttpClient：建立一个可以关闭的httpClient
        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-operation/after-sale-mgr/order/return/status");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("cookie", "X-SSPP-UID=Cm8KLly1T2VLgTLMA4QnAg==; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%2216a61f389935b4-0b2eb777907c4b-366d7e04-1296000-16a61f3899481a%22%7D; _ga=GA1.2.974017705.1561297447; qa4_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTM2MzksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDM2Mzk0MjAsXCJleHBcIjoxNTY0ODg1NjM5NDIwLFwiaWF0XCI6MTU2MjI5MzYzOTQyMCxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiYjc0NzA3ZDMtMWM2Ni00YTg5LTllYTktYzEwMjQ2M2UwOTg0XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODU2Mzl9.7y2_X_4U-qm-56Ee4IwAC5uBaOjlmTxN4NOAr2sbAZs; username=yebeibei; qa7_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTc5ODgsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDc5ODg4NDMsXCJleHBcIjoxNTY0ODg5OTg4ODQzLFwiaWF0XCI6MTU2MjI5Nzk4ODg0MyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiNThlZGU3ZjQtMTM5ZC00ZjkwLWI1ODMtMjljNGFlNGYyNjQ1XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODk5ODh9.mlv_M7Rdo_aJ-Ekg5kw_XZsYxmP02_pZF5KnKDCp1pI; sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjYzNDUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzYzNDU5NjMsXCJleHBcIjoxNTY0OTE4MzQ1OTYzLFwiaWF0XCI6MTU2MjMyNjM0NTk2MyxcImlwXCI6XCIyMTguMjQxLjEzMC4yMTBcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiY2NmYjdmY2MtOGY5Ny00Y2E0LWI2NjctOGU5NzcxOWVmNTdiXCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ5MTgzNDV9.DTxBafWQk31cgzyh10PWi9LjdWzH1D1PGg48zRPGJFU; qa9_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjcxODksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzcxODk1NjcsXCJleHBcIjoxNTY0OTE5MTg5NTY3LFwiaWF0XCI6MTU2MjMyNzE4OTU2NyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiOTEzNjE4YzgtY2VhNC00OGFmLTg5OGItZTkwODZiNDc3ZTY4XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6MjY3MyxcInVzZXJOYW1lXCI6XCJ5ZWJlaWJlaVwifSIsImV4cCI6MTU2NDkxOTE4OX0.exR5oX6OJGfxDHZCXwlwxNYKBZjQftUCnFQmKTru4ic");

            //获取返回值
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                System.out.println("POST请求返回的数据是：" + result);
            }
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {     //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    //3、单个退单退款确认详情
    @Test(description = "退款确认详情")
    public void orderDetail() {
        //CloseableHttpClient：建立一个可以关闭的httpClient
        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-operation/after-sale-mgr/order/return/detail");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("cookie", "X-SSPP-UID=Cm8KLly1T2VLgTLMA4QnAg==; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%2216a61f389935b4-0b2eb777907c4b-366d7e04-1296000-16a61f3899481a%22%7D; _ga=GA1.2.974017705.1561297447; qa4_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTM2MzksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDM2Mzk0MjAsXCJleHBcIjoxNTY0ODg1NjM5NDIwLFwiaWF0XCI6MTU2MjI5MzYzOTQyMCxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiYjc0NzA3ZDMtMWM2Ni00YTg5LTllYTktYzEwMjQ2M2UwOTg0XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODU2Mzl9.7y2_X_4U-qm-56Ee4IwAC5uBaOjlmTxN4NOAr2sbAZs; username=yebeibei; qa7_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTc5ODgsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDc5ODg4NDMsXCJleHBcIjoxNTY0ODg5OTg4ODQzLFwiaWF0XCI6MTU2MjI5Nzk4ODg0MyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiNThlZGU3ZjQtMTM5ZC00ZjkwLWI1ODMtMjljNGFlNGYyNjQ1XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODk5ODh9.mlv_M7Rdo_aJ-Ekg5kw_XZsYxmP02_pZF5KnKDCp1pI; sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjYzNDUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzYzNDU5NjMsXCJleHBcIjoxNTY0OTE4MzQ1OTYzLFwiaWF0XCI6MTU2MjMyNjM0NTk2MyxcImlwXCI6XCIyMTguMjQxLjEzMC4yMTBcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiY2NmYjdmY2MtOGY5Ny00Y2E0LWI2NjctOGU5NzcxOWVmNTdiXCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ5MTgzNDV9.DTxBafWQk31cgzyh10PWi9LjdWzH1D1PGg48zRPGJFU; qa9_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjcxODksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzcxODk1NjcsXCJleHBcIjoxNTY0OTE5MTg5NTY3LFwiaWF0XCI6MTU2MjMyNzE4OTU2NyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiOTEzNjE4YzgtY2VhNC00OGFmLTg5OGItZTkwODZiNDc3ZTY4XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6MjY3MyxcInVzZXJOYW1lXCI6XCJ5ZWJlaWJlaVwifSIsImV4cCI6MTU2NDkxOTE4OX0.exR5oX6OJGfxDHZCXwlwxNYKBZjQftUCnFQmKTru4ic");
            //发送的参数数据{"returnOrderId":"231777","refundNo":"178298383156928512"}
            Map<String, String> map = new HashMap<String, String>();
            map.put("returnOrderId", "231777");
            map.put("refundNo", "178298383156928512");
            //设置发送的数据
            StringEntity s = new StringEntity(JSON.toJSONString(map));
            s.setContentEncoding("UTF-8");
            post.setEntity(s);
            //获取返回值
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                System.out.println("POST请求返回的数据是：" + result);
            }
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            Assert.assertTrue(false);
        } finally {
            try {     //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


        //4、单个退单confirmDetail详情
        @Test(description = "退单确认")
        public void confirmDetail() {
            //CloseableHttpClient：建立一个可以关闭的httpClient
            //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            try {
                HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-operation/after-sale-mgr/refund/confirmDetail");
                post.setConfig(requestConfig);
                //设置头信息
                post.setHeader("content-type", "application/json");
                post.setHeader("cookie", "X-SSPP-UID=Cm8KLly1T2VLgTLMA4QnAg==; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%2216a61f389935b4-0b2eb777907c4b-366d7e04-1296000-16a61f3899481a%22%7D; _ga=GA1.2.974017705.1561297447; qa4_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTM2MzksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDM2Mzk0MjAsXCJleHBcIjoxNTY0ODg1NjM5NDIwLFwiaWF0XCI6MTU2MjI5MzYzOTQyMCxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiYjc0NzA3ZDMtMWM2Ni00YTg5LTllYTktYzEwMjQ2M2UwOTg0XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODU2Mzl9.7y2_X_4U-qm-56Ee4IwAC5uBaOjlmTxN4NOAr2sbAZs; username=yebeibei; qa7_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTc5ODgsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDc5ODg4NDMsXCJleHBcIjoxNTY0ODg5OTg4ODQzLFwiaWF0XCI6MTU2MjI5Nzk4ODg0MyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiNThlZGU3ZjQtMTM5ZC00ZjkwLWI1ODMtMjljNGFlNGYyNjQ1XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODk5ODh9.mlv_M7Rdo_aJ-Ekg5kw_XZsYxmP02_pZF5KnKDCp1pI; sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjYzNDUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzYzNDU5NjMsXCJleHBcIjoxNTY0OTE4MzQ1OTYzLFwiaWF0XCI6MTU2MjMyNjM0NTk2MyxcImlwXCI6XCIyMTguMjQxLjEzMC4yMTBcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiY2NmYjdmY2MtOGY5Ny00Y2E0LWI2NjctOGU5NzcxOWVmNTdiXCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ5MTgzNDV9.DTxBafWQk31cgzyh10PWi9LjdWzH1D1PGg48zRPGJFU; qa9_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjcxODksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzcxODk1NjcsXCJleHBcIjoxNTY0OTE5MTg5NTY3LFwiaWF0XCI6MTU2MjMyNzE4OTU2NyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiOTEzNjE4YzgtY2VhNC00OGFmLTg5OGItZTkwODZiNDc3ZTY4XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6MjY3MyxcInVzZXJOYW1lXCI6XCJ5ZWJlaWJlaVwifSIsImV4cCI6MTU2NDkxOTE4OX0.exR5oX6OJGfxDHZCXwlwxNYKBZjQftUCnFQmKTru4ic");
                //发送的参数数据{"returnOrderId":"231777","refundNo":"178298383156928512"}
                Map<String, String> map = new HashMap<String, String>();
                map.put("refundNo", "178298383156928512");
                //设置发送的数据
                StringEntity s = new StringEntity(JSON.toJSONString(map));
                s.setContentEncoding("UTF-8");
                post.setEntity(s);
                //获取返回值
                CloseableHttpResponse res = closeableHttpClient.execute(post);
                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                    System.out.println("POST请求返回的数据是：" + result);
                }
            } catch (Exception e) {
                System.out.println("发生异常：" + e.getMessage());
                Assert.assertTrue(false);
            } finally {
                try {     //关闭流并释放资源
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }



    //5、售后问题字典树
    @Test(description = "售后问题字典树")
    public void refundAfterSaleLevelTree() {
        //CloseableHttpClient：建立一个可以关闭的httpClient
        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-operation/after-sale-mgr/refund/afterSale/levelTree");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("cookie", "X-SSPP-UID=Cm8KLly1T2VLgTLMA4QnAg==; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%2216a61f389935b4-0b2eb777907c4b-366d7e04-1296000-16a61f3899481a%22%7D; _ga=GA1.2.974017705.1561297447; qa4_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTM2MzksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDM2Mzk0MjAsXCJleHBcIjoxNTY0ODg1NjM5NDIwLFwiaWF0XCI6MTU2MjI5MzYzOTQyMCxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiYjc0NzA3ZDMtMWM2Ni00YTg5LTllYTktYzEwMjQ2M2UwOTg0XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODU2Mzl9.7y2_X_4U-qm-56Ee4IwAC5uBaOjlmTxN4NOAr2sbAZs; username=yebeibei; qa7_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIyOTc5ODgsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MDc5ODg4NDMsXCJleHBcIjoxNTY0ODg5OTg4ODQzLFwiaWF0XCI6MTU2MjI5Nzk4ODg0MyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiNThlZGU3ZjQtMTM5ZC00ZjkwLWI1ODMtMjljNGFlNGYyNjQ1XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ4ODk5ODh9.mlv_M7Rdo_aJ-Ekg5kw_XZsYxmP02_pZF5KnKDCp1pI; sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjYzNDUsInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzYzNDU5NjMsXCJleHBcIjoxNTY0OTE4MzQ1OTYzLFwiaWF0XCI6MTU2MjMyNjM0NTk2MyxcImlwXCI6XCIyMTguMjQxLjEzMC4yMTBcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiY2NmYjdmY2MtOGY5Ny00Y2E0LWI2NjctOGU5NzcxOWVmNTdiXCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6Mjg5MyxcInVzZXJOYW1lXCI6XCLlj7botJ3otJ1cIn0iLCJleHAiOjE1NjQ5MTgzNDV9.DTxBafWQk31cgzyh10PWi9LjdWzH1D1PGg48zRPGJFU; qa9_sso_jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NjIzMjcxODksInN1YiI6IntcImFsbG93X2V4cFwiOjE1NjQ5MzcxODk1NjcsXCJleHBcIjoxNTY0OTE5MTg5NTY3LFwiaWF0XCI6MTU2MjMyNzE4OTU2NyxcImlwXCI6XCIxNzIuMTYuMi4yNDFcIixcImlzc1wiOlwic29uZ3NodXBpbnBpbi5jb21cIixcInNlc3Npb25JZFwiOlwiOTEzNjE4YzgtY2VhNC00OGFmLTg5OGItZTkwODZiNDc3ZTY4XCIsXCJzeXNDb2RlXCI6XCJvcFwiLFwidXNlcklkXCI6MjY3MyxcInVzZXJOYW1lXCI6XCJ5ZWJlaWJlaVwifSIsImV4cCI6MTU2NDkxOTE4OX0.exR5oX6OJGfxDHZCXwlwxNYKBZjQftUCnFQmKTru4ic");

            //获取返回值
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                System.out.println("POST请求返回的数据是：" + result);

            }
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
            Assert.assertTrue(false);
            assert(false);
        } finally {
            try {     //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }






}
