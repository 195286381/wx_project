package com.github.binarywang.demo.wx.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 *    微信页面路由
 * </pre>
 * @author xzzzz
 * @since 2018/11/20
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/hello")
    public String HelloPage() {
        return "hello";
    }
}
