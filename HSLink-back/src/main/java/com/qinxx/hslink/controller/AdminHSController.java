package com.qinxx.hslink.controller;

import com.qinxx.hslink.model.HsWinners;
import com.qinxx.hslink.model.base.AjaxResult;
import com.qinxx.hslink.service.AdminHSService;
import com.qinxx.hslink.service.IHsWinnersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHY
 */
@RestController
@RequestMapping("/admin")
public class AdminHSController extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LogManager.getLogger(HSController.class);

    @Resource
    AdminHSService adminhsService;
    @Resource
    private IHsWinnersService hsWinnersService;


    /**
     * 获取待审核文章列表
     * @param param
     * @return
     */
    @PostMapping("/getVerifyList")
    public Map<String, Object> getVerifyList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getVerifyList(param);
        return result;
    }

    /**
     * 审核文章（通过/驳回）
     * @param param
     * @return
     */
    @PostMapping("/verifyArticle")
    public Map<String, Object> verifyArticle(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.verifyArticle(param);
        return result;
    }

    /**
     * 获取人员管理列表
     * @param param
     * @return
     */
    @PostMapping("/getPersonnelManagement")
    public Map<String, Object> getPersonnelManagement(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getPersonnelManagement(param);
        return result;
    }

    /**
     * 删除人员信息
     */
    @DeleteMapping("/person/{ids}")
    public AjaxResult removePerson(@PathVariable String[] ids)
    {
        return toAjax(adminhsService.deletePersonByIds(ids));
    }

    /**
     * 获取人员详情
     * @param param
     * @return
     */
    @PostMapping("/getPersonInfo")
    public Map<String, Object> getPersonInfo(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getPersonInfo(param);
        return result;
    }

    /**
     * 人员管理（冻结/解冻）
     * @param param
     * @return
     */
    @PostMapping("/peopleManagement")
    public Map<String, Object> peopleManagement(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.peopleManagement(param);
        return result;
    }

    /**
     * 获取帮助答复列表
     * @param param
     * @return
     */
    @PostMapping("/getHelpAnswerList")
    public Map<String, Object> getHelpAnswerList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getHelpAnswerList(param);
        return result;
    }

    /**
     * 解答帮助
     * @param param
     * @return
     */
    @PostMapping("/answerHelp")
    public Map<String, Object> answerHelp(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.answerHelp(param);
        return result;
    }

    /**
     * 获取注册管理数据
     * @param param
     * @return
     */
    @PostMapping("/getRegisterManagementData")
    public Map<String, Object> getRegisterManagementData(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getRegisterManagementData(param);
        return result;
    }

    /**
     * 通过注册
     * @param param
     * @return
     */
    @PostMapping("/passRegister")
    public Map<String, Object> passRegister(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.passRegister(param);
        return result;
    }

    /**
     * 获取文我的页面未操作数字
     * @param param
     * @return
     */
    @PostMapping("/getMyPageNumber")
    public Map<String, Object> getMyPageNumber(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.getMyPageNumber(param);
        return result;
    }

    /**
     * 上传轮播图
     * @param param
     * @return
     */
    @PostMapping("/uploadRotationPhoto")
    public Map<String, Object> uploadRotationPhoto(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.uploadRotationPhoto(param);
        return result;
    }

    /**
     * 删除轮播图
     * @param param
     * @return
     */
    @PostMapping("/deleteRotationPhoto")
    public Map<String, Object> deleteRotationPhoto(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = adminhsService.deleteRotationPhoto(param);
        return result;
    }

    /**
     * 查询获奖列表
     */
    @GetMapping("/winners/list")
    public AjaxResult listWinners(HsWinners hsWinners)
    {
        List<HsWinners> list = hsWinnersService.selectHsWinnersList(hsWinners);
        return AjaxResult.success(list);
    }

    /**
     * 新增获奖
     */
    @PostMapping("/winners")
    public AjaxResult addWinners(@RequestBody HsWinners hsWinners)
    {
        return toAjax(hsWinnersService.insertHsWinners(hsWinners));
    }

    /**
     * 修改获奖
     */
    @PutMapping("/winners")
    public AjaxResult editWinners(@RequestBody HsWinners hsWinners)
    {
        return toAjax(hsWinnersService.updateHsWinners(hsWinners));
    }

    /**
     * 删除获奖
     */
    @DeleteMapping("/winners/{ids}")
    public AjaxResult removeWinners(@PathVariable Long[] ids)
    {
        return toAjax(hsWinnersService.deleteHsWinnersByIds(ids));
    }

    /**
     * 查询班级列表
     */
    @PostMapping("/class/list")
    public AjaxResult listClass(@RequestBody Map<String, Object> params)
    {
        List<Map<String, Object>> list = adminhsService.selectClassList(params);
        return AjaxResult.success(list);
    }

    /**
     * 查询职务列表
     */
    @PostMapping("/post/list")
    public AjaxResult listPost(@RequestBody Map<String, Object> params)
    {
        List<Map<String, Object>> list = adminhsService.selectPostList(params);
        return AjaxResult.success(list);
    }

}
