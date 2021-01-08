package com.qinxx.hslink.service.impl;

import com.qinxx.hslink.dao.AdminHSMapper;
import com.qinxx.hslink.service.AdminHSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHY
 */
@Service
public class AdminHSServiceImpl implements AdminHSService {

    /**
     * 日志
     */
    private static Logger logger = LogManager.getLogger(HSServiceImpl.class);
    @Resource
    AdminHSMapper adminHSMapper;

    /**文件路径*/
    @Value("${filePath}")
    private String filesPath;
    /**
     * 获取待审核文章数据
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getVerifyList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = adminHSMapper.getVerifyList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取人员管理列表
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getPersonnelManagement(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = adminHSMapper.getPersonnelManagement(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 审核文章（通过/驳回）
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> verifyArticle(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.verifyArticle(param);
            adminHSMapper.verifyArticleNotice(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 人员管理（冻结/解冻）
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> peopleManagement(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.peopleManagement(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取帮助答复列表
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getHelpAnswerList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = adminHSMapper.getHelpAnswerList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 解答帮助
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> answerHelp(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.answerHelp(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取文我的页面未操作数字
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getMyPageNumber(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = adminHSMapper.getMyPageNumber(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 通过注册
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> passRegister(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.passRegister(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取注册管理数据
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getRegisterManagementData(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = adminHSMapper.getRegisterManagementData(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 上传轮播图
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> uploadRotationPhoto(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.uploadRotationPhoto(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> deleteRotationPhoto(Map<String, Object> param) {
        int res = 0;
        //Linux环境下的路径
//        File file = new File(filesPath + "/" + param.get("fileEncryption"));
        //Window环境下的路径
        File file = new File(filesPath + "\\" + param.get("fileEncryption"));
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        Map<String, Object> result = new HashMap<>();
        try {
            res = adminHSMapper.deleteRotationPhoto(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }
}
