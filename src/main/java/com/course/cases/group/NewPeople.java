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


/**
 * C端通过社区id获取新人专享数据
 */

public class NewPeople {

    //设置请求超时时间
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(60000)
            .setConnectTimeout(60000)
            .setConnectionRequestTimeout(60000)
            .build();

    String aa=null;
    @Test(priority = 1,description = "新人专享")
    public void newPerp() {
        //CloseableHttpClient：建立一个可以关闭的httpClient
        //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-community-activity/newPeople/V2/list");
            post.setConfig(requestConfig);
            //设置头信息
            post.setHeader("content-type", "application/json");
            post.setHeader("It-token", "eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50U3RhdHVzIjoxLCJhY2NvdW50SWQiOjE3NTY0NTU5NDI2NTczMTA3MiwiYWNjb3VudFR5cGUiOiJ3bXAiLCJyZXF1ZXN0VXJpIjpudWxsLCJzZXNzaW9uSWQiOiI4ZWIzMmVjOS1lYjJkLTRmZDUtOWJhNC1mNmE3ZDQ3ZDA2ODgiLCJwcm9kVHlwZSI6IlNTUFAiLCJleHAiOjE1NjQzMTEyNjUsInVzZXJJZCI6MTc1NjQ1NjAxNzg2MTE4MTQ0fQ.JCyOcrTbY7PAYNT8zLkRON5vRlrRAzJYyewmMesN6MITAG_XcAdh2APYScVOdWoVBiwOgwVxRysNwbksWQWt0g");
            //发送的参数数据
            Map<String, String> map = new HashMap<String, String>();
            map.put("communityId", "165569480093372416");
            //设置发送的数据
            StringEntity s = new StringEntity(JSON.toJSONString(map));
            s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            //获取返回值
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            aa="2222";
            int ss = res.getStatusLine().getStatusCode();
            System.out.println(ss);
            String     actname_test;
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                JSONObject jsonObject = JSONObject.parseObject(result)  ;
                JSONArray tasklist = jsonObject.getJSONArray("body");
                for (int j=0;j<tasklist.size();j++){
                    actname_test=  tasklist.getJSONObject(j).getString("actName");
                    if (actname_test!=null){
                        System.out.println(actname_test);
                    }
                }




                System.out.println( JSONObject.parseObject(result));

                System.out.println("POST请求返回的数据是：" + result);
                Assert.assertTrue(true);


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

        @Test(priority = 2,description = "退单列表页状态")
        public void returnStatus() {
            //CloseableHttpClient：建立一个可以关闭的httpClient
            //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            aa=aa+"dd";
            System.out.println(aa);
            try {
                HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-community-activity/newPeople/V2/list");
                post.setConfig(requestConfig);
                //设置头信息
                post.setHeader("content-type", "application/json");
                post.setHeader("It-token", "eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50U3RhdHVzIjoxLCJhY2NvdW50SWQiOjE3NTY0NTU5NDI2NTczMTA3MiwiYWNjb3VudFR5cGUiOiJ3bXAiLCJyZXF1ZXN0VXJpIjpudWxsLCJzZXNzaW9uSWQiOiI4ZWIzMmVjOS1lYjJkLTRmZDUtOWJhNC1mNmE3ZDQ3ZDA2ODgiLCJwcm9kVHlwZSI6IlNTUFAiLCJleHAiOjE1NjQzMTEyNjUsInVzZXJJZCI6MTc1NjQ1NjAxNzg2MTE4MTQ0fQ.JCyOcrTbY7PAYNT8zLkRON5vRlrRAzJYyewmMesN6MITAG_XcAdh2APYScVOdWoVBiwOgwVxRysNwbksWQWt0g");
                //发送的参数数据
                Map<String, String> map = new HashMap<String, String>();
                map.put("communityId", "165569480093372416");
                //设置发送的数据
                StringEntity s = new StringEntity(JSON.toJSONString(map));
                s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
                post.setEntity(s);
                //获取返回值
                CloseableHttpResponse res = closeableHttpClient.execute(post);
                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                    System.out.println("POST请求返回的数据是：" + result);
                    Assert.assertTrue(true);

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
            @Test(description = "新人专享1")
            public void returnNew() {
                //CloseableHttpClient：建立一个可以关闭的httpClient
                //这样使得创建出来的HTTP实体，可以被Java虚拟机回收掉，不至于出现一直占用资源的情况。
                CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                try {
                    HttpPost post = new HttpPost("https://qa9m.songshupinpin.com/shop-community-activity/newPeople/V2/list");
                    post.setConfig(requestConfig);
                    //设置头信息
                    post.setHeader("content-type","application/json");
                    post.setHeader("It-token","eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50U3RhdHVzIjoxLCJhY2NvdW50SWQiOjE3NTY0NTU5NDI2NTczMTA3MiwiYWNjb3VudFR5cGUiOiJ3bXAiLCJyZXF1ZXN0VXJpIjpudWxsLCJzZXNzaW9uSWQiOiI4ZWIzMmVjOS1lYjJkLTRmZDUtOWJhNC1mNmE3ZDQ3ZDA2ODgiLCJwcm9kVHlwZSI6IlNTUFAiLCJleHAiOjE1NjQzMTEyNjUsInVzZXJJZCI6MTc1NjQ1NjAxNzg2MTE4MTQ0fQ.JCyOcrTbY7PAYNT8zLkRON5vRlrRAzJYyewmMesN6MITAG_XcAdh2APYScVOdWoVBiwOgwVxRysNwbksWQWt0g");
                    //发送的参数数据
                    Map<String,String> map =new HashMap<String,String>();
                    map.put("communityId","165569480093372416");
                    //设置发送的数据
                    StringEntity s = new StringEntity(JSON.toJSONString(map));
                    s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
                    post.setEntity(s);
                    //获取返回值
                    CloseableHttpResponse res = closeableHttpClient.execute(post);
                    if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                        System.out.println("POST请求返回的数据是："+result);
                        Assert.assertTrue(true);

                    }
                }
                catch (Exception e){
                    System.out.println("发生异常："+e.getMessage());
                    Assert.assertTrue(false);
                }
                finally {
                    try {     //关闭流并释放资源
                        closeableHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



    }












}
