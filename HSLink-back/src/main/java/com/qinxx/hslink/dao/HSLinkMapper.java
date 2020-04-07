package com.qinxx.hslink.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HSLinkMapper {
    /**
     *登录验证
     * @return
     */
    Map<String,Object> login(Map<String,Object> param);

    /**
     * 注册
     */
    int register(Map<String,Object> param);

    /**
     * 获取校园通知
     */
    List<Map<String,Object>> getSchoolNotice(Map<String,Object> param);

    /**
     * 获取优秀建议
     */
    List<Map<String,Object>> getParentAdvice(Map<String,Object> param);

    /**
     * 获取神奇想法
     */
    List<Map<String,Object>> getStudentThinking(Map<String,Object> param);

    /**
     * 获取一条数据具体内容
     */
    Map<String,Object> getOneContent(Map<String,Object> param);

    /**
     * 获取一条数据具体内容
     */
    List<Map<String,Object>> getOneLeaveMessage(Map<String,Object> param);

    /**
     * 新增留言
     */
    int addMessage(Map<String,Object> param);
}
