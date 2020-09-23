package com.qinxx.hslink.util;

import com.qinxx.hslink.service.HSService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.主要用于标记配置类，兼备Component的效果。
 * @author PHY
 */
@Configuration
/**
 * 2.开启定时任务
 */
@EnableScheduling
public class SaticScheduleTask {

    @Resource
    HSService hsService;

    /**
     * 3.添加定时任务
     */
    @Scheduled(cron = "0/5 * * * * ?")
    /**
     * 或直接指定时间间隔，例如：5秒
     */
    /*@Scheduled(fixedRate=5000)*/

    private void configureTasks() {
        Map<String, Object> map = new HashMap<>();
        map = hsService.updateIntegral();
        System.err.println(map.get("taskIntegral") + "" + LocalDateTime.now());
    }
}