package com.itaxi.server.filters;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String header = request.getHeader("user-Agent");

        if("OPTIONS".equalsIgnoreCase(request.getMethod()) || "HEAD".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else if("TRACE".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        else {
            chain.doFilter(req, res);
//            if(!header.contains("Mozilla/5.0")){
//                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
//            }
//            else{
//
//            }

        }
    }

    @Override
    public void destroy() {

    }
}
