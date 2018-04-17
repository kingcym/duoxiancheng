package com.example.demo.weixinAuth;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/16 16:49
 */
@RestController
@RequestMapping("/weixin")
public class LoginControler {

    @GetMapping("/applogin")
    public void applogin(HttpServletResponse response) throws IOException {
        //回调地址
       // String backUrl = "http://19278m1w03.imwork.net/weixin/appCallBack";
        String backUrl = "http://app.mamaezhan.com";
        /**
         * 参考官网：
         * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
         */
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.appID
                + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code&"
                + "scope=snsapi_userinfo"
                + "&state=STATE"
                + "#wechat_redirect";
        response.sendRedirect(url);
    }


    @GetMapping("/pclogin")
    public void pclogin(HttpServletResponse response) throws IOException {
        //回调地址
        String backUrl = "http://m.qinziezhan.com/weixin/appCallBack";
        /**
         * 参考官网：
         * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN
         */
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + AuthUtil.appID
                + "&redirect_uri=" + backUrl
                + "&response_type=code"
                + "&scope=snsapi_login"
                + "&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }

    @GetMapping("/appCallBack")
    public String appCallBack(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.appID
                + "&secret=" + AuthUtil.appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject json = AuthUtil.doGetJson(url);
        System.err.println("--------------------------------------------");
        System.out.println(json);
        System.err.println("---------------------------------------------");
        String openid = json.getString("openid");
        String token = json.getString("access_token");
        //拉取用户信息
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                + "&openid=OPENID" + openid
                + "&lang=zh_CN";
        JSONObject infoJson = AuthUtil.doGetJson(infoUrl);
        System.err.println("=============================================");
        System.out.println(infoJson);
        System.err.println("=============================================");
        return "文文是猪";
    }


}
