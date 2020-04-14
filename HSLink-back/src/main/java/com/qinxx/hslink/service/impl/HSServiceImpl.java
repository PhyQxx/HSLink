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
    public Map<String, Object> addClassNotice(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addClassNotice(param);
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

    @Override
    public Map<String, Object> updatePassword(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updatePassword(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updateOneContent(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updateOneContent(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> deleteOne(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.deleteOne(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> deleteOneMessage(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.deleteOneMessage(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getPersonalInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> res1 = hsLinkMapper.getListByAttribute(param);
        Map<String, Object> res2 = hsLinkMapper.getPersonalInfo(param);
        res.put("personalList",res1);
        res.put("personalInfo",res2);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getPersonalPrivateLetter(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getPersonalPrivateLetter(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> readPrivateLetter(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> res1 = hsLinkMapper.getPersonalPrivateLetter(param);
        int res2 = hsLinkMapper.updateUnread(param);
        res.put("onePrivateLetter",res1);
        res.put("readStatus",res2);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> sendLetter(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.sendLetter(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> deleteLetter(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.deleteLetter(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }
}
