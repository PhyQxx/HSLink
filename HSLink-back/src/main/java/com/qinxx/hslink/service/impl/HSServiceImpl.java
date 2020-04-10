package com.qinxx.hslink.service.impl;

import com.qinxx.hslink.dao.HSLinkMapper;
import com.qinxx.hslink.service.HSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class HSServiceImpl implements HSService {

    @Autowired
    HSLinkMapper hsLinkMapper;

    @Override
    public Map<String, Object> login(Map<String, Object> param) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> res = hsLinkMapper.login(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> register(Map<String, Object> parameter) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.register(parameter);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getAllContent(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> schoolNoticeList = hsLinkMapper.getSchoolNotice(param);
        res.put("schoolNoticeList",schoolNoticeList);
        List<Map<String,Object>> parentAdvice = hsLinkMapper.getParentAdvice(param);
        res.put("parentAdvice",parentAdvice);
        List<Map<String,Object>> studentThinking = hsLinkMapper.getStudentThinking(param);
        res.put("studentThinking",studentThinking);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getOneContent(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> res1 = hsLinkMapper.getOneContent(param);
        List<Map<String, Object>> res2 = hsLinkMapper.getOneLeaveMessage(param);
        res.put("content",res1);
        res.put("message",res2);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getListByAttribute(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getListByAttribute(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> addMessage(Map<String, Object> param) {
        int res = 0;
        Map<String, Object> result = new HashMap<>();
        try {
            res = hsLinkMapper.addMessage(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> addArticle(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addArticle(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getClassInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> res1 = hsLinkMapper.getClassBulletin(param);
        List<Map<String, Object>> res2 = hsLinkMapper.getClassNotice(param);
        List<Map<String, Object>> res3 = hsLinkMapper.getStudents(param);
        res.put("bulletin",res1);
        res.put("noticeList",res2);
        res.put("students",res3);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> addBulletin(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addBulletin(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updateBulletin(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updateBulletin(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }
}
