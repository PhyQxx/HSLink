package com.qinxx.hslink.controller;

import com.qinxx.hslink.service.AdminHSService;
import com.qinxx.hslink.service.HSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author PHY
 */
@Controller
@RequestMapping("/admin")
public class AdminHSController {

    /**
     * 日志
     */
    private static Logger logger = LogManager.getLogger(HSController.class);

    @Resource
    AdminHSService AdminhsService;

    /**
     * 获取待审核文章列表
     * @param param
     * @return
     */
    @PostMapping("/getVerifyList")
    @ResponseBody
    public Map<String, Object> getVerifyList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.getVerifyList(param);
        return result;
    }

    /**
     * 审核文章（通过/驳回）
     * @param param
     * @return
     */
    @PostMapping("/verifyArticle")
    @ResponseBody
    public Map<String, Object> verifyArticle(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.verifyArticle(param);
        return result;
    }

    /**
     * 获取人员管理列表
     * @param param
     * @return
     */
    @PostMapping("/getPersonnelManagement")
    @ResponseBody
    public Map<String, Object> getPersonnelManagement(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.getPersonnelManagement(param);
        return result;
    }

    /**
     * 人员管理（冻结/解冻）
     * @param param
     * @return
     */
    @PostMapping("/peopleManagement")
    @ResponseBody
    public Map<String, Object> peopleManagement(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.peopleManagement(param);
        return result;
    }

    /**
     * 获取帮助答复列表
     * @param param
     * @return
     */
    @PostMapping("/getHelpAnswerList")
    @ResponseBody
    public Map<String, Object> getHelpAnswerList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.getHelpAnswerList(param);
        return result;
    }

    /**
     * 解答帮助
     * @param param
     * @return
     */
    @PostMapping("/answerHelp")
    @ResponseBody
    public Map<String, Object> answerHelp(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.answerHelp(param);
        return result;
    }

    /**
     * 获取注册管理数据
     * @param param
     * @return
     */
    @PostMapping("/getRegisterManagementData")
    @ResponseBody
    public Map<String, Object> getRegisterManagementData(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.getRegisterManagementData(param);
        return result;
    }

    /**
     * 通过注册
     * @param param
     * @return
     */
    @PostMapping("/passRegister")
    @ResponseBody
    public Map<String, Object> passRegister(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.passRegister(param);
        return result;
    }

    /**
     * 获取文我的页面未操作数字
     * @param param
     * @return
     */
    @PostMapping("/getMyPageNumber")
    @ResponseBody
    public Map<String, Object> getMyPageNumber(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = AdminhsService.getMyPageNumber(param);
        return result;
    }

}
