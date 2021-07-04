package com.udacity.jwdnd.course1.cloudstorage.interceptor;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ValidateUserInterceptor implements HandlerInterceptor {
    private UserService userService;

    public ValidateUserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);
        if (user == null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return  false;
        }
        return true;
    }
}
