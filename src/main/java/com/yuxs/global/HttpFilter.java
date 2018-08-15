package com.yuxs.global;


import com.yuxs.example.threadLocal.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request2 = (HttpServletRequest) request;
        LOGGER.info("doFilter,{},{}", Thread.currentThread().getId(), request2.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
