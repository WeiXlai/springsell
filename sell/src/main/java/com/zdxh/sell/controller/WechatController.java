package com.zdxh.sell.controller;

import com.zdxh.sell.config.ProjectUrlConfig;
import com.zdxh.sell.enums.ResultEnum;
import com.zdxh.sell.exception.SellException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        String url=projectUrlConfig.getWechatMpAuthorize()+"/sell/wechat/userInfo";
        String appid=wxMpService.getWxMpConfigStorage().getAppId();
        System.out.println("mpAppId是:"+appid);
        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        System.out.println("enter............");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            System.out.println("微信授权:"+e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        System.out.println("微信的OpendId:"+openId);

        return "redirect:" + returnUrl + "?openid=" + openId;
    }




}
