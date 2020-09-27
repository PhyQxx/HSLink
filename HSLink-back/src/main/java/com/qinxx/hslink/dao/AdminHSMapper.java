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
}
