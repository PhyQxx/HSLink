package com.qinxx.hslink.service;

import java.util.Map;

/**
 * @author PHY
 */
public interface AdminHSService {

    /**
     * 获取待审核列表
     * @param param
     * @return
     */
    Map<String, Object> getVerifyList(Map<String, Object> param);

    /**
     * 获取人员管理列表
     * @param param
     * @return
     */
    Map<String, Object> getPersonnelManagement(Map<String, Object> param);

    /**
     * 审核文章（通过/驳回）
     * @param param
     * @return
     */
    Map<String, Object> verifyArticle(Map<String, Object> param);

    /**
     * 人员管理（冻结/解冻）
     * @param param
     * @return
     */
    Map<String, Object> peopleManagement(Map<String, Object> param);

    /**
     * 获取帮助答复列表
     * @param param
     * @return
     */
    Map<String, Object> getHelpAnswerList(Map<String, Object> param);

    /**
     * 解答帮助
     * @param param
     * @return
     */
    Map<String, Object> answerHelp(Map<String, Object> param);
}
