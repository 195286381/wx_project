package com.github.binarywang.demo.wx.mp.handler;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import com.github.binarywang.demo.wx.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        String strContent = wxMessage.getContent().toLowerCase().trim();
        String content;
        switch (strContent) {
            case "1": {
                content = "昆仑微信商城服务中心\n" +
                    "\n" +
                    "客服电话：010-89258896\n" +
                    "客户电话：010-89289899\n" +
                    "网络营销服务电话：01089258898";
            }
            break;
            case "2": {
                content = "开发中";
            }
            break;
            case "3": {
                content = "开发中";
            }
            break;
            case "4": {
                content = "开发中";
            }
            break;
            case "5": {
                content = "开发中";
            }
            break;
            case "6": {
                content = "开发中";
            }
            break;

            default: {
                content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
            }
            break;
        }

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
