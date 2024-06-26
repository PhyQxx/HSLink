package com.qinxx.hslink.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author PHY
 */
@Mapper
public interface AdminHSMapper {

    /**
     * 获取待审核文章数据
     * @param param
     * @return
     */
    List<Map<String, Object>> getVerifyList(Map<String, Object> param);

    /**
     * 获取人员管理列表
     * @param param
     * @return
     */
    List<Map<String, Object>> getPersonnelManagement(Map<String, Object> param);

    /**
     * 审核文章（通过/驳回）
     * @param param
     * @return
     */
    int verifyArticle(Map<String, Object> param);

    /**
     * 审核文章通知
     * @param param
     * @return
     */
    int verifyArticleNotice(Map<String, Object> param);

    /**
     * 人员管理（冻结/解冻）
     * @param param
     * @return
     */
    int peopleManagement(Map<String, Object> param);

    /**
     * 获取帮助答复列表
     * @param param
     * @return
     */
    List<Map<String, Object>> getHelpAnswerList(Map<String, Object> param);

    /**
     * 解答帮助
     * @param param
     * @return
     */
    int answerHelp(Map<String, Object> param);

    /**
     * 获取文我的页面未操作数字
     * @param param
     * @return
     */
    Map<String, Object> getMyPageNumber(Map<String, Object> param);

    /**
     * 通过注册
     * @param param
     * @return
     */
    int passRegister(Map<String, Object> param);

    /**
     * 获取注册管理数据
     * @param param
     * @return
     */
    List<Map<String, Object>> getRegisterManagementData(Map<String, Object> param);

    /**
     * 上传轮播图
     * @param param
     * @return
     */
    int uploadRotationPhoto(Map<String, Object> param);

    /**
     * 删除轮播图
     * @param param
     * @return
     */
    int deleteRotationPhoto(Map<String, Object> param);

    /**
     * 获取人员详情
     * @param param
     * @return
     */
    Map<String, Object> getPersonInfo(Map<String, Object> param);

    /***
     * 删除人员
     * @param ids
     * @return
     */
    int deletePersonByIds(String[] ids);

    /**
     * 获取班级列表
     * @param params
     * @return
     */
    List<Map<String, Object>> selectClassList(Map<String, Object> params);

    /**
     * 获取职务列表
     * @param params
     * @return
     */
    List<Map<String, Object>> selectPostList(Map<String, Object> params);
}
