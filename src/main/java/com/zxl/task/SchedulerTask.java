package com.zxl.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description: 定时任务
 */
@Slf4j
@Component
@EnableScheduling
public class SchedulerTask {
    /**
     * cron表达式为六个域，表示秒、分、时、每月第几天、月、星期
     *  * : 表示匹配该域的任意值，比如在秒*, 就表示每秒都会触发事件。
     *  / : 表示起始时间开始触发，然后每隔固定时间触发一次，例如在分域使用5/20,则意味着5分，25分，45分，分别触发一次.
     *  ? : 只能用在每月第几天和星期两个域。表示不指定值
     *   - : 表示范围，例如在分域使用5-20，表示从5分到20分钟每分钟触发一次
     */
//    @Scheduled(cron="*/6 * * * * ?")
//    private void process1() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        log.info("当前时间:{}", currentTime);
//    }

    /**
     * fixedRate 上一次开始执行时间点之后6秒再执行
     * Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
     * Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     */
//    @Scheduled(fixedRate = 6000)
//    private void process2() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        log.info("当前时间2:{}", currentTime);
//    }
}
