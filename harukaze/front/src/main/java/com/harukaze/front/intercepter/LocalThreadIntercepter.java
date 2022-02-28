package com.harukaze.front.intercepter;

import com.harukaze.front.util.UserThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PackageName: com.harukaze.front.intercepter
 * @ClassName: LocalThreadIntercepter
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 9:56 AM
 */
@Component
public class LocalThreadIntercepter implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
