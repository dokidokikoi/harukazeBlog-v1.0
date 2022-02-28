package com.harukaze.front.aop;

import cn.hutool.json.JSONUtil;
import com.harukaze.front.entity.SysLog;
import com.harukaze.front.mapper.SysLogMapper;
import com.harukaze.front.service.SysLogService;
import com.harukaze.front.util.HttpContextUtils;
import com.harukaze.front.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    SysLogService logService;

    @Autowired
    SysLogMapper logMapper;

    @Pointcut("@annotation(com.harukaze.front.aop.LogAnnotation)")
    public void pc(){}

    @Around("pc()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long time = System.currentTimeMillis() - beginTime;
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        SysLog sysLog = new SysLog();
        sysLog.setCreateDate(System.currentTimeMillis());
        sysLog.setMethod(signature.getName());
        sysLog.setModule(logAnnotation.module());
        sysLog.setOperation(logAnnotation.operator());
        //请求参数
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] != null) {
            Field[] fields=args[0].getClass().getDeclaredFields();
            String[] fieldNames=new String[fields.length];
            for(int i=0;i<fields.length;i++){
                System.out.println(fields[i].getType());
                fieldNames[i]=fields[i].getName();
            }
            String params = JSONUtil.toJsonPrettyStr(fieldNames);
            sysLog.setParams(params);
        }
        //获取request, 设置ip地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IpUtils.getIpAddr(request));
        sysLog.setBrowser(request.getHeader("User-Agent"));
        sysLog.setTime(time);
        sysLog.setAddress(IpUtils.getIpAddress(sysLog.getIp()));
        logMapper.insert(sysLog);

        log.info("=======================log============================");
        log.info("module:{}",sysLog.getModule());
        log.info("operation:{}",sysLog.getOperation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("request method:{}",className+"."+sysLog.getMethod()+"()");
        log.info("params:{}",sysLog.getParams());
        log.info("ip:{}", sysLog.getIp());
        log.info("excute time : {} ms", sysLog.getTime());
        log.info("browser:{}", sysLog.getBrowser());
        log.info("=======================log end============================");
    }


}











