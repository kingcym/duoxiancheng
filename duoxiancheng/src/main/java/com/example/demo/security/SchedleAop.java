package com.example.demo.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;



/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/27 17:57
 */
//@Aspect
//@Component
public class SchedleAop {
    @Autowired
    ThreadPoolTaskScheduler ThreadPoolTaskScheduler;

    private static String batchExecHost = "wwwww";

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=================进入AOP==========================================");
        if (!allowedBatchExec()) {
            System.out.println("===================不执行线程=====================");
            ThreadPoolTaskScheduler.shutdown();
            return null;
        }
        try {
            System.out.println("===================执行线程=====================");
            return pjp.proceed();
        } catch (Exception e) {
            return null;
        }
    }
    private boolean allowedBatchExec() {
        if ("wwwww1".equals(batchExecHost)) {
            return true;
        }
        return false;
    }

}
