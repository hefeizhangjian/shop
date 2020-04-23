package com.zj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.aspect
 * @date 2020/4/21 13:57
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 监控service层运行时间的日志切面
 */
@Component
@Aspect
public class ServiceLogAspect {
   static final Logger LOGGER= LoggerFactory.getLogger(ServiceLogAspect.class);

    /*
    AOP通知:
    1.前置通知:在方法调用前执行
    2.后置通知:在方法#正确#运行后执行
    3.环绕通知:在方法调用之前和之后,都分别可以执行
    4.异常通知:在方法调用过程发生异常时执行
    5.最终通知:在方法调用之后执行(区别于后置通知)
     */
    /**
     * @Description: 切面表达式:
     *                  execution 代表所要执行的表达是主体
     *                  第一处 * 代表方法返回类型 *代表所有类型
     *                  第二处 包名代表AOP监控的类所在的包
     *                  第三处 .. 代表当前包及其子包下的所有类方法
     *                  第四处 * 代表类名  *代表所有的类
     *                  第五处 *(..) *代表类中的方法名,(..)代表方法中的任何参数
     * @param joinPoint:
     * @return: Object
     * @Author: 张建 (119855181@qq.com)
     * @date: 2020/4/21
     */
    @Around("execution(* com.zj.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint){

        LOGGER.info("====== 开始执行 {}.{} ======",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        long beginTime=System.currentTimeMillis();
        Object result = null;
        try {
            result=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long takeTime = endTime-beginTime;
        if (takeTime>3000){
            LOGGER.error("====== 执行 {}.{} 结束,耗时{}毫秒======",joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),takeTime);
        }else if (takeTime>2000){
            LOGGER.warn("====== 执行 {}.{} 结束,耗时{}毫秒======",joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),takeTime);
        }else{
            LOGGER.info("====== 执行 {}.{} 结束,耗时{}毫秒======",joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),takeTime);
        }
    return result;
    }
}
