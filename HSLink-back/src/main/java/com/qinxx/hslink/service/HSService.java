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
     * 查询某些条件数据
     */
    Map<String,Object> getListByAttribute(Map<String,Object> param);

    /**
     * 新增留言
     */
    Map<String,Object> addMessage(Map<String,Object> param);

    /**
     * 新增一条数据
     */
    Map<String,Object> addArticle(Map<String,Object> param);

    /**
     * 新增一条班级通知
     */
    Map<String,Object> addClassNotice(Map<String,Object> param);

    /**
     * 获取班级信息
     */
    Map<String,Object> getClassInfo(Map<String,Object> param);

    /**
     * 新增班级公告
     */
    Map<String,Object> addBulletin(Map<String,Object> param);

    /**
     * 修改班级公告
     */
    Map<String,Object> updateBulletin(Map<String,Object> param);

    /**
     * 修改密码
     */
    Map<String,Object> updatePassword(Map<String,Object> param);

    /**
     * 编辑一条数据具体内容
     */
    Map<String,Object> updateOneContent(Map<String,Object> param);

    /**
     * 删除一条数据
     */
    Map<String,Object> deleteOne(Map<String,Object> param);

    /**
     * 删除一条留言
     */
    Map<String,Object> deleteOneMessage(Map<String,Object> param);

    /**
     * 获取个人信息
     */
    Map<String,Object> getPersonalInfo(Map<String,Object> param);

    /**
     * 获取个人信息
     */
    Map<String,Object> getPersonalPrivateLetter(Map<String,Object> param);

    /**
     * 阅读私信
     */
    Map<String,Object> readPrivateLetter(Map<String,Object> param);

    /**
     * 发送私信
     */
    Map<String,Object> sendLetter(Map<String,Object> param);
 }