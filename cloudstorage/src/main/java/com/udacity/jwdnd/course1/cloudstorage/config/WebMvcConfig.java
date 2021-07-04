package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.interceptor.ValidateUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ValidateUserInterceptor validateUserInterceptor;

    public WebMvcConfig(ValidateUserInterceptor validateUserInterceptor) {
        this.validateUserInterceptor = validateUserInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validateUserInterceptor).addPathPatterns("/home/**");
    }
}
