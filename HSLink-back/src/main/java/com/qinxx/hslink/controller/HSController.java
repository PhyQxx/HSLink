package com.qinxx.hslink.controller;

import com.qinxx.hslink.service.HSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
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

    //查询某些条件数据
    @RequestMapping(value = "/getListByAttribute", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getListByAttribute(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getListByAttribute(param);
        return result;
    }

    //新增留言
    @RequestMapping(value = "/addMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addMessage(param);
        return result;
    }

    //新增一条数据
    @RequestMapping(value = "/addArticle", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addArticle(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addArticle(param);
        return result;
    }

    //新增一条班级通知
    @RequestMapping(value = "/addClassNotice", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addClassNotice(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addClassNotice(param);
        return result;
    }

    //获取班级信息
    @RequestMapping(value = "/getClassInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getClassInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getClassInfo(param);
        return result;
    }

    //新增班级公告
    @RequestMapping(value = "/addBulletin", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addBulletin(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addBulletin(param);
        return result;
    }

    //修改班级公告
    @RequestMapping(value = "/updateBulletin", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateBulletin(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updateBulletin(param);
        return result;
    }

    //修改密码
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updatePassword(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updatePassword(param);
        return result;
    }

    //编辑一条数据具体内容
    @RequestMapping(value = "/updateOneContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateOneContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updateOneContent(param);
        return result;
    }

    //删除一条数据
    @RequestMapping(value = "/deleteOne", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteOne(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteOne(param);
        return result;
    }

    //删除一条留言
    @RequestMapping(value = "/deleteOneMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteOneMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteOneMessage(param);
        return result;
    }

    //获取个人信息
    @RequestMapping(value = "/getPersonalInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getPersonalInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getPersonalInfo(param);
        return result;
    }

    //获取个人私信
    @RequestMapping(value = "/getPersonalPrivateLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getPersonalPrivateLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getPersonalPrivateLetter(param);
        return result;
    }

    //阅读私信
    @RequestMapping(value = "/readPrivateLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> readPrivateLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.readPrivateLetter(param);
        return result;
    }

    //发送私信
    @RequestMapping(value = "/sendLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> sendLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.sendLetter(param);
        return result;
    }

    //删除一条私信
    @RequestMapping(value = "/deleteLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteLetter(param);
        return result;
    }
}
