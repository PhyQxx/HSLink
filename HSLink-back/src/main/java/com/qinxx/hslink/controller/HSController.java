package com.qinxx.hslink.controller;

import com.qinxx.hslink.service.HSService;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 帅气的布里茨
 */
@Controller
@RequestMapping("/hs")
public class HSController {

    /**
     * 日志
     */
    private static Logger logger = LogManager.getLogger(HSController.class);

    @Resource
    HSService hsService;

    /**登录验证*/
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> hello(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>(32);
        result = hsService.login(param);
        return result;
    }

    /**注册*/
    @RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> register(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = new HashMap<>(32);
        result = hsService.register(param);
        return result;
    }

    /**获取首页内容*/
    @RequestMapping(value = "/getAllContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getAllContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getAllContent(param);
        return result;
    }

    /**获取一条数据具体内容*/
    @RequestMapping(value = "/getOneContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getOneContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getOneContent(param);
        return result;
    }

    /**查询某些条件数据*/
    @RequestMapping(value = "/getListByAttribute", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getListByAttribute(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getListByAttribute(param);
        return result;
    }

    /**新增留言*/
    @RequestMapping(value = "/addMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addMessage(param);
        return result;
    }

    /**新增一条数据*/
    @RequestMapping(value = "/addArticle", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addArticle(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addArticle(param);
        return result;
    }

    /**新增一条班级通知*/
    @RequestMapping(value = "/addClassNotice", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addClassNotice(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addClassNotice(param);
        return result;
    }

    /**获取班级信息*/
    @RequestMapping(value = "/getClassInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getClassInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getClassInfo(param);
        return result;
    }

    /**新增班级公告*/
    @RequestMapping(value = "/addBulletin", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addBulletin(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addBulletin(param);
        return result;
    }

    /**修改班级公告*/
    @RequestMapping(value = "/updateBulletin", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateBulletin(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updateBulletin(param);
        return result;
    }

    /**修改密码*/
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updatePassword(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updatePassword(param);
        return result;
    }

    /**编辑一条数据具体内容*/
    @RequestMapping(value = "/updateOneContent", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateOneContent(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updateOneContent(param);
        return result;
    }

    /**删除一条数据*/
    @RequestMapping(value = "/deleteOne", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteOne(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteOne(param);
        return result;
    }

    /**删除一条留言*/
    @RequestMapping(value = "/deleteOneMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteOneMessage(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteOneMessage(param);
        return result;
    }

    /**获取个人信息*/
    @RequestMapping(value = "/getPersonalInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getPersonalInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getPersonalInfo(param);
        return result;
    }

    /**获取个人私信*/
    @RequestMapping(value = "/getPersonalPrivateLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getPersonalPrivateLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getPersonalPrivateLetter(param);
        return result;
    }

    /**app获取个人私信*/
    @RequestMapping(value = "/getPersonalPrivateLetterApp", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getPersonalPrivateLetterApp(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getPersonalPrivateLetterApp(param);
        return result;
    }

    /**app获取两个人的私信*/
    @RequestMapping(value = "/getTwoLetterApp", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getTwoLetterApp(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getTwoLetterApp(param);
        return result;
    }

    /**
     * 私信改为已读
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateReadApp", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updateReadApp(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updateReadApp(param);
        return result;
    }

    /**阅读私信*/
    @RequestMapping(value = "/readPrivateLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> readPrivateLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.readPrivateLetter(param);
        return result;
    }

    /**发送私信*/
    @RequestMapping(value = "/sendLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> sendLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.sendLetter(param);
        return result;
    }

    /**删除一条私信*/
    @RequestMapping(value = "/deleteLetter", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> deleteLetter(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.deleteLetter(param);
        return result;
    }

    /**更新个人积分*/
    @RequestMapping(value = "/timingTask", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> timingTask(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.timingTask(param);
        return result;
    }

    /**
     * 使用 httpServletRequest作为参数
     * @param  httpServletRequest
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest httpServletRequest){
        return hsService.upload(httpServletRequest);
    }

    /**
     * 获取文件列表
     */
    @PostMapping("/getFileList")
    @ResponseBody
    public Map<String, Object> getFileList(String[] fileList){
        Map<String, Object> result = new HashMap<>();
        result = hsService.getFileList(fileList);
        return result;
    }

    /**
     * 文件下载
     */
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
        hsService.fileDownload(request.getParameter("fileId"),request,response);
    }

    /**
     * 查询轮播图列表
     */
    @PostMapping("/getRotationPhotoList")
    @ResponseBody
    public Map<String, Object> getRotationPhotoList(){
        Map<String, Object> result = new HashMap<>();
        result = hsService.getRotationPhotoList();
        return result;
    }

    /**
     * 获取关注列表
     * @param param
     * @return
     */
    @PostMapping("/getFollowList")
    @ResponseBody
    public Map<String, Object> getFollowList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = hsService.getFollowList(param);
        return result;
    }

    /**
     * 取消关注
     * @param param
     * @return
     */
    @PostMapping("/cancelFollow")
    @ResponseBody
    public Map<String, Object> cancelFollow(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = hsService.cancelFollow(param);
        return result;
    }

    /**
     * 加关注
     * @param param
     * @return
     */
    @PostMapping("/addFollow")
    @ResponseBody
    public Map<String, Object> addFollow(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = hsService.addFollow(param);
        return result;
    }

    /**
     * 获取关注人员列表
     * @param param
     * @return
     */
    @PostMapping("/getFollowPeopleList")
    @ResponseBody
    public Map<String, Object> getFollowPeopleList(@RequestBody Map<String,Object> param){
        Map<String, Object> result = new HashMap<>();
        result = hsService.getFollowPeopleList(param);
        return result;
    }

    /**修改手机号*/
    @RequestMapping(value = "/updatePhone", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updatePhone(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updatePhone(param);
        return result;
    }

    /**
     * 获取字典项数据
     * @param param
     * @return
     */
    @RequestMapping(value = "/getDictionariesData", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getDictionariesData(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getDictionariesData(param);
        return result;
    }

    /**
     * 获取帮助历史提问列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getHistoryHelpList", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getHistoryHelpList(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.getHistoryHelpList(param);
        return result;
    }

    /**
     * 新增一条帮助提问
     * @param param
     * @return
     */
    @RequestMapping(value = "/addHelpQuestion", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addHelpQuestion(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.addHelpQuestion(param);
        return result;
    }

    /**
     * 编辑个人信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/updatePersonalInfo", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> updatePersonalInfo(@RequestBody Map<String,Object> param) {
        Map<String,Object> result = hsService.updatePersonalInfo(param);
        return result;
    }

    /**
     * 测试接口
     */
    @PostMapping("/test")
    @ResponseBody
    public Map<String, Object> test(String page, String limit){
        Map<String, Object> result = new HashMap<>();

        return result;
    }

}
