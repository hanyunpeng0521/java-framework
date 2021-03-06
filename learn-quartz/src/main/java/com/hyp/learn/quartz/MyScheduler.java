package com.hyp.learn.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.quartz
 * hyp create at 19-12-14
 **/
public class MyScheduler {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1. 创建 SchedulerFactory
        SchedulerFactory factory = new StdSchedulerFactory();
        // 2. 从工厂中获取调度器实例
        Scheduler scheduler = factory.getScheduler();

        // 3. 引进作业程序,创建JobDetail实例，并与HelloJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withDescription("this is a ram job") //job的描述
                .withIdentity("jobTest", "jobTestGrip") //job 的name和group
                .build();

        long time = System.currentTimeMillis() + 3 * 1000L; //3秒后启动任务
        Date statTime = new Date(time);

        // 4. 创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("this is a cronTrigger")
                .withIdentity("jobTrigger", "jobTriggerGroup")
                // .startNow()//立即生效
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(5)//每隔5s执行一次
//                        .repeatForever())//一直执行
                .startAt(statTime)  //默认当前时间启动
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) //5秒执行一次
                .build();

        // 5. 注册任务和定时器
        scheduler.scheduleJob(jobDetail, trigger);


        System.out.println("--------scheduler start ! ------------");

        // 6. 启动 调度器
        scheduler.start();
        _log.info("启动时间 ： " + new Date());

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");
    }
}
