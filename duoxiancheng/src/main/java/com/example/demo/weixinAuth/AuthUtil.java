package com.example.demo.weixinAuth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/16 15:27
 */
public class AuthUtil {
    public final static String appID = "wx39c064f7fdf78b61";

    /**
     *  公众号密码
     */
    public final static String appSecret = "b2754ef4e73e48de85edf5b431a322e7";


    //应用id
    //public static final String appID = "wx90be2514ffca6a63";
    public static final String pcID = "wxbdc5610cc59c1631";
    //应用密钥
      // public static final String appSecret = "1c87fa7007226f98f52892cd27ab37b8";
   // public static final String appSecret = "88ca52017cb23d2199939ab49d1cc8a5";

    public static JSONObject doGetJson(String url) {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            //创建HttpClients对象
            client = HttpClients.createDefault();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(url);
            // 执行请求
            response = client.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                //转成String
                String result =  entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
                //String转json
                jsonObject = JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

}
