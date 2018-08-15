package com.yuxs.global;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {

    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new HttpFilter());
        bean.addUrlPatterns("/threadLocal/*");
        return bean;
    }
}
