package com.frame.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("Accept");
        String header1 = request.getHeader("header1");
        String path = request.getServletPath();
        String whiteList = ".*/((loginapi)|(web-static)|(test)).*";
        if (path.matches(whiteList)) {
            return true;
        }
        render(response);
        return false;
    }

    private void render(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = "请登录";
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
