package com.capgemini.cn.deemo.aspect;

import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.data.domain.OperationLog;
import com.capgemini.cn.deemo.service.OperationLogService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author hasaker
 * @since 2019/9/11 09:45
 */
@Aspect
@Component
public class OperationLogAspect {

    private final OperationLogService operationLogService;

    public OperationLogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    /***
     * 定义service切入点拦截规则，拦截OperationLog注解的方法
     */
    @Pointcut("@annotation(com.capgemini.cn.deemo.annotation.ControllerLog)")
    public void controllerAspect(){}

    /**
     * 切面 通知配置
     */
    @AfterReturning("controllerAspect()")
    public void addOperationLog(JoinPoint joinPoint) {
        //保存日志
        OperationLog operationLog = new OperationLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        // 获取操作
        ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);
        if (controllerLog != null) {
            String name = controllerLog.name();
            operationLog.setOperationName(name);
        }

        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        // 获取请求的参数
        String args = Arrays.toString(joinPoint.getArgs());

        operationLog.setOperationDesc(args);

        // 获取operationLogId
        operationLog.setOperationId(IdWorker.get().nextId());
        // 获取操作用户Id
        operationLog.setOperationUserId(UserUtils.getCurrentUser().getUserId());
        // 获取操作时间
        operationLog.setOperationTime(new java.sql.Timestamp(System.currentTimeMillis()));


        operationLogService.insertOperationLog(operationLog);
    }
}
