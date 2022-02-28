package com.harukaze.front.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.harukaze.front.entity.SysUser;
import com.harukaze.front.service.SysUserService;
import com.harukaze.front.util.JwtUtils;
import com.harukaze.front.util.RedisUtil;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserService userService;

    @Autowired
    AccountServiceImpl accountService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
    * @Description:
    * @Author: doki
    * @Param: [request, response, chain]
    * @return: void
    * @Date: 26/11/2021 2:42 PM
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, RuntimeException {
        String token = request.getHeader(JwtUtils.getHeader());
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2tpIiwiaWF0IjoxNjM3MjAxNjI3LCJleHAiOjE2Mzc4MDY0Mjd9.iy5BjJXX6QTIdGQAYVfLtpvQie9f03ozITninOvmL3awSxokQf_oPm5EgD0fQqGzioAWuZK74cguBLA8zmYiRQ";
        if (StrUtil.isBlank(token)) {
            chain.doFilter(request, response);
            return;
        }
        Claims claim = JwtUtils.getClaimByToken(token);
        try {
            if (claim == null) {
                throw new ServletException("token已过期");
            }
            if (JwtUtils.isTokenExpired(claim)) {
                throw new JwtException("token已过期");
            }
            String username = claim.getSubject();
            if (StrUtil.isBlank((String) redisUtil.get("token_"+token))) {
                throw new JwtException("token已过期");
            }
            SysUser user = userService.findUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            UserThreadLocal.set(userService.findUserById(user.getId()));

            UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(username, null, accountService.getAuthorities(user));
            SecurityContextHolder.getContext().setAuthentication(token1);
        } catch (Exception e) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(e.getMessage())));
            return;
        }

        chain.doFilter(request, response);
    }

}
