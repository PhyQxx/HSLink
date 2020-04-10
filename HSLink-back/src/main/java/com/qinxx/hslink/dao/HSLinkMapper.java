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
     * 查询某些条件数据
     */
    List<Map<String,Object>> getListByAttribute(Map<String,Object> param);

    /**
     * 新增留言
     */
    int addMessage(Map<String,Object> param);

    /**
     * 新增文章
     */
    int addArticle(Map<String,Object> param);

    /**
     * 获取班级公告
     */
    Map<String,Object> getClassBulletin(Map<String,Object> param);

    /**
     * 获取班级公告
     */
    List<Map<String,Object>> getClassNotice(Map<String,Object> param);

    /**
     * 获取班级成员
     */
    List<Map<String,Object>> getStudents(Map<String,Object> param);

    /**
     * 新增班级公告
     */
    int addBulletin(Map<String,Object> param);

    /**
     * 修改班级公告
     */
    int updateBulletin(Map<String,Object> param);
}
