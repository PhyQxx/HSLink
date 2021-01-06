package com.qinxx.hslink.service.impl;

import com.qinxx.hslink.controller.HSController;
import com.qinxx.hslink.dao.HSLinkMapper;
import com.qinxx.hslink.service.HSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HSServiceImpl implements HSService {

    /**
     * 日志
     */
    private static Logger logger = LogManager.getLogger(HSServiceImpl.class);
    @Resource
    HSLinkMapper hsLinkMapper;
    /**文件路径*/
    @Value("${filePath}")
    private String filePath;

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
            hsLinkMapper.addMessageNotice(param);
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
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> res1 = hsLinkMapper.getPersonalPrivateLetter(param);
        List<Map<String, Object>> res2 = hsLinkMapper.getSentPrivateLetter(param);
        res.put("privateLetter",res1);
        res.put("sentLetter",res2);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> readPrivateLetter(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        Integer type = (Integer) param.get("type");
        List<Map<String, Object>> res1;
        int res2;
        if (type == 0) {
            res1 = hsLinkMapper.getPersonalPrivateLetter(param);
            res2= hsLinkMapper.updateUnread(param);
        } else {
            res1 = hsLinkMapper.getSentPrivateLetterContent(param);
            res2= 0;
        }
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
        Integer type = (Integer) param.get("type");
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        if (type == 0) {
            try {
                res = hsLinkMapper.deleteLetter(param);
            } catch (Exception e) {
                e.printStackTrace();
                res = 0;
            }
        } else {
            try {
                res = hsLinkMapper.deleteSentLetter(param);
            } catch (Exception e) {
                e.printStackTrace();
                res = 0;
            }
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> timingTask(Map<String, Object> param) {
        /**定时刷新*/
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        /**更新个人积分*/
        int res1 = hsLinkMapper.updateOneIntegral(param);
        if (res1 == 1) {
            res.put("taskIntegral","更新个人积分成功");
        } else {
            res.put("taskIntegral","更新个人积分失败");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res.put("time",df.format(new Date()));
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> upload(HttpServletRequest httpServletRequest) {
        boolean flag = false;
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        //强制转换为MultipartHttpServletRequest接口对象 (它包含所有HttpServletRequest的方法)
        if(httpServletRequest instanceof MultipartHttpServletRequest){
            multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
        }else{
            return dealResultMap(false, "上传失败", new HashMap<>());
        }
        //获取MultipartFile文件信息(注意参数为前端对应的参数名称)
        MultipartFile mf = multipartHttpServletRequest.getFile("file");
        //获取源文件名称
        String fileName = mf.getOriginalFilename();
        //存储路径可在配置文件中指定
        File pfile = new File(filePath);
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
        String id = UUID.randomUUID().toString();
        File file = new File(pfile,id+fileName);
        /* //指定好存储路径
        File file = new File(fileName);*/
        try {
            //保存文件
            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败", new HashMap<>());
        }
        //保存文件路径
        Map<String,Object> param = new HashMap<>();
        param.put("id", id);
        param.put("fileName",fileName);
        param.put("fileEncryption",id+fileName);
        param.put("filePath",String.valueOf(file));
        hsLinkMapper.insertFilePath(param);
        return dealResultMap(true, "上传成功", param);
    }

    @Override
    public Map<String, Object> getFileList(String[] fileList) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        res.put("fileList",hsLinkMapper.getFileList());
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public void fileDownload(String fileId,HttpServletRequest request, HttpServletResponse response) {
        // 下载的文件名
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("NAME","test.png");
        fileMap.put("ID","");
        String fileName = fileMap.get("NAME");
        String id = fileMap.get("ID");
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 文件路径
        String path = "D:\\office\\phy\\HSLink\\HSLink-back\\src\\main\\static\\files\\test.png";
        InputStream bis = null;
        BufferedOutputStream out = null;
        // 获取输入流
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(path)), 1024 * 10);
            // 假如以中文名下载的话
            // 转码，免得文件名中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            int i = bis.available();
            byte[] buff = new byte[i];
            while ((len = bis.read(buff)) > 0) {
                out.write(buff, 0, len);
                out.flush();
            }
        } catch (FileNotFoundException e) {
            logger.error("文件未找到" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码异常：" + e.getMessage());
        } catch (IOException e) {
            logger.error("IO异常：" + e.getMessage());
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
            } catch (IOException e) {
                logger.error("流关闭异常" + e.getMessage());
            }
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("流关闭异常" + e.getMessage());
            }
        }
    }

    @Override
    public Map<String, Object> getRotationPhotoList() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        res.put("rotationPhotoList",hsLinkMapper.getRotationPhotoList());
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取个人私信
     * @param param
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> getPersonalPrivateLetterApp(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> list = hsLinkMapper.getLetterOtherInfo(param);
        for (Map<String, Object> item : list) {
            Map<String, Object> res = new HashMap<>();
            res.put("userInfo",item);
            param.put("otherId",item.get("user_id"));
            List<Map<String, Object>> letterList = hsLinkMapper.getCommunicationLetter(param);
            res.put("letterList",letterList);
            resultList.add(res);
        }
        result.put("data",resultList);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getTwoLetterApp(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getCommunicationLetter(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updateReadApp(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updateReadApp(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getFollowList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getFollowList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> cancelFollow(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.cancelFollow(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> addFollow(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addFollow(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getFollowPeopleList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getFollowPeopleList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getFansPeopleList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getFansPeopleList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updatePhone(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updatePhone(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getDictionariesData(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getDictionariesData(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getHistoryHelpList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getHistoryHelpList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> addHelpQuestion(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addHelpQuestion(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updatePersonalInfo(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updatePersonalInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updateIntegral() {
        Map<String, Object> result = new HashMap<>();
        int res1 = hsLinkMapper.updateIntegral();
        if (res1 > 0) {
            result.put("taskIntegral","更新个人积分成功");
        } else {
            result.put("taskIntegral","更新个人积分失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> addCollection(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.addCollection(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> cancelCollection(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.cancelCollection(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> getCollectionList(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> res = hsLinkMapper.getCollectionList(param);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    @Override
    public Map<String, Object> updateReadNumber(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            res = hsLinkMapper.updateReadNumber(param);
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取tabBar数字
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getTabBarNumber(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        res.put("unreadNumber",hsLinkMapper.getTabBarUnreadNumber(param));
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 获取通知数据
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getNoticeData(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> res1 = hsLinkMapper.getMessageNotice(param);
        List<Map<String, Object>> res2 = hsLinkMapper.getVerifyNotice(param);
        res.put("messageNoticeList",res1);
        res.put("verifyNoticeList",res2);
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 设置通知为已读
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> updateNoticeRead(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        int res = 0;
        try {
            if ("0".equals(param.get("type"))) {
                res = hsLinkMapper.updateMessageNoticeRead(param);
            } else {
                res = hsLinkMapper.updateVerifyNoticeRead(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = 0;
        }
        result.put("data",res);
        result.put("success",true);
        return result;
    }

    /**
     * 返回参数
     * @param flag
     * @param message
     * @return
     */
    private Map<String, Object> dealResultMap(boolean flag,
                                              String message,
                                              Map<String, Object> fileInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("flag",flag);
        map.put("message",message);
        map.put("fileInfo", fileInfo);
        return map;
    }
}
