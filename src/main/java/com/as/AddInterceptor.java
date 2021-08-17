package com.as;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new Interceptor())
            .addPathPatterns("/async");
}
}
