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
     * 新增一篇文章
     */
    int addArticle(Map<String,Object> param);

    /**
     * 新增一条班级通知
     */
    int addClassNotice(Map<String,Object> param);

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

    /**
     * 修改密码
     */
    int updatePassword(Map<String,Object> param);

    /**
     * 编辑一条数据具体内容
     */
    int updateOneContent(Map<String,Object> param);

    /**
     * 删除一条数据
     */
    int deleteOne(Map<String,Object> param);

    /**
     * 删除一条留言
     */
    int deleteOneMessage(Map<String,Object> param);

    /**
     * 获取个人信息
     */
    Map<String,Object> getPersonalInfo(Map<String,Object> param);

    /**
     * 获取个人私信内容
     */
    List<Map<String,Object>> getPersonalPrivateLetter(Map<String,Object> param);

    /**
     * 获取已读私信内容
     */
    List<Map<String,Object>> getSentPrivateLetterContent(Map<String,Object> param);

    /**
     * 获取已发送私信
     */
    List<Map<String,Object>> getSentPrivateLetter(Map<String,Object> param);

    /**
     * 将未读私信置位已读
     */
    int updateUnread(Map<String,Object> param);

    /**
     * 发送私信
     */
    int sendLetter(Map<String,Object> param);

    /**
     * 删除一条私信
     */
    int deleteLetter(Map<String,Object> param);

    /**
     * 删除一条已读私信
     */
    int deleteSentLetter(Map<String,Object> param);

    /**
     * 更新个人积分
     */
    int updateIntegral(Map<String,Object> param);

}
