package com.hyl.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author HeYonglin
 * @date 2019-06-11 16:56
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("AuthorityInterceptor -> preHandle");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authority authority = method.getAnnotation(Authority.class);
        if (authority == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        // 这里为了方便直接传递了参数
        // 一般的做法是用户第一次登录，将信息放到session中
        // 以后每次操作时从request中获取session，从session中获取用户信息
        // 然后根据用户信息从数据库中查权限信息
        String userAuthority = httpServletRequest.getParameter("userAuthority");
        if (!userAuthority.equals("admin")) {
            // 脱离了Spring MVC的返回流程，重新编码
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.print("没有权限");
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
