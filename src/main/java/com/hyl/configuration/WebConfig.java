package com.hyl.configuration;

import com.hyl.common.AuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author HeYonglin
 * @date 2019-06-11 17:07
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
    //addPathPatterns：用于设置拦截器的过滤路径规则
    //excludePathPatterns：用于设置不需要拦截的过滤规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**");
    }
}
