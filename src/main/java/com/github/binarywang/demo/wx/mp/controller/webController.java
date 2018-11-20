package com.github.binarywang.demo.wx.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
}
