package com.qinxx.hslink.service;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface HSService {

    /**
     *登录验证
     * @return
     */
    Map<String,Object> login(Map<String,Object> param);

    /**
     * 注册
     */
    Map<String, Object> register(Map<String, Object> parameter);

    /**
     * 获取首页内容
     */
    Map<String,Object> getAllContent(Map<String,Object> param);

    /**
     * 获取一条数据具体内容
     */
    Map<String,Object> getOneContent(Map<String,Object> param);

    /**
     * 新增留言
     */
    Map<String,Object> addMessage(Map<String,Object> param);
 }