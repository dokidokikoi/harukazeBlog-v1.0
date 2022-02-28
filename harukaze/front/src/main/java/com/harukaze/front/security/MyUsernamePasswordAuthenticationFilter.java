package com.harukaze.front.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harukaze.front.util.CryptoAESUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @PackageName: com.harukaze.front.security
 * @ClassName: MyUsernamePasswordAuthenticationFilter
 * @Description:
 * @Author: doki
 * @Date: 2022/1/4 19:18
 */

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("仅支持POST请求");
        }

        String username = "";
        String password = "";

        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
                username = authenticationBean.get("username");
                password = authenticationBean.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            username = obtainUsername(request);
            password = obtainPassword(request);
        }

        System.out.println(username + "==========================================" + password);
        try {
            password = CryptoAESUtil.decrypt(password, null);
        } catch (Exception e) {

        }
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        System.out.println(password+"=========================================================");
        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return super.getAuthenticationManager().authenticate(authRequest);
    }
}
