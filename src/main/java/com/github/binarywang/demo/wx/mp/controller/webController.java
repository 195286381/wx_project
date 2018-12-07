package com.github.binarywang.demo.wx.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *    网页功能路由
 * </pre>
 * @author xzzzz
 * @since 2018/11/20
 */
@Controller
@RequestMapping("/web")
public class webController {
    @ResponseBody
    @GetMapping("/hello")
    public Object hello() {
        Map<String, String> info = new HashMap<>();
        info.put("hello", "world");
        return info;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object userUpload(Part userImage) {
        String filename = userImage.getSubmittedFileName();
        try {
            userImage.write(filename);
        } catch (IOException ex) {
            ex.printStackTrace();
            return dealResultMap(false, "upload failed");
        }
        return dealResultMap(true, "upload success!");
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }
}
