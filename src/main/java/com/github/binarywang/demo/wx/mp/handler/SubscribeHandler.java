package com.github.binarywang.demo.wx.mp.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        // String msg_bak = "感谢你对武汉昆仑润滑的关注!!!";
        String msg = "感谢辣么美的你对武汉昆仑润滑油微信公众号的关注！\n" +
            "点击下方【微信商城】菜单，即可享受昆仑油品线上购买优惠；\n" +
            "\n" +
            "\n" +
            "如您需了解更多功能，请回复数字：\n" +
            "【1】即可进入“微信商城客户服务中心”；\n" +
            "【2】即可登录“昆仑润滑油官方微网”；\n" +
            "【3】了解更多，昆仑润滑油杯·2017中国量产车性能大赛相关详情；\n" +
            "【4】昆仑润滑产品，一目了然；\n" +
            "【5】获取更多区域服务信息；\n" +
            "【6】客服中心，欢迎您的咨询！\n" +
            "\n" +
            "再次感谢您对昆仑润滑油的关注与支持！";

        try {
            return new TextBuilder().build(msg, wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
