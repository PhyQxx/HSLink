package com.qinxx.hslink.controller;

import com.qinxx.hslink.service.HSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hs")
public class HSController {

    @Autowired
    HSService hsService;

    //登录验证
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> hello(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>();
        result = hsService.login(param);
        return result;
    }

    //注册
    @RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> register(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>();
        result = hsService.register(param);
        return result;
    }

    //获取首页内容
    @RequestMapping(value = "/getAllContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getAllContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>();
        result = hsService.getAllContent(param);
        return result;
    }

    //获取一条数据具体内容
    @RequestMapping(value = "/getOneContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getOneContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getOneContent(param);
        return result;
    }

    //新增留言
    @RequestMapping(value = "/addMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addMessage(param);
        return result;
    }

}
