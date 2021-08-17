package com.as;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class MyAspect {
    private final static Logger log = LoggerFactory.getLogger(MyAspect.class);
    private final String POINT_CUT = "execution(public * com.as.AsyncController.*(..))";

    //定义切点位置:下面如果你在SSM中用AOP，在xml中配的就是下面
    @Pointcut(POINT_CUT)
    public void performance() {
    }
    /**
     * 前置通知
     *
     * @throws Throwable
     */
//    @After("performance()")
    @After("performance()")
    public void doAfter(JoinPoint joinPoint) {
//        User object = (User)joinPoint.getArgs()[0];
//        User1 object1 = (User1)joinPoint.getArgs()[1];
////        Class<?> aClass = joinPoint.getTarget().getClass();
////        System.out.println(aClass.getCanonicalName());
////        User object = (User) aClass;
////        User user = (User) joinPoint.getTarget();
////        User user1 = (User) joinPoint.getThis();
//        System.out.println(object.toString());
//        System.out.println(object1.toString());
        // 接收到请求，记录请求内容
//        log.info("doAfter");
//        System.out.println("1111111");
    }
}
