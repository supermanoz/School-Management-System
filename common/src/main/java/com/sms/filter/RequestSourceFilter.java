package com.sms.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
//@PropertySource("classpath:application.properties")
public class RequestSourceFilter extends OncePerRequestFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(RequestSourceFilter.class);
    private String AUTH_TOKEN_HEADER_NAME;
    private String AUTH_TOKEN;

    public RequestSourceFilter(){
    }
    public RequestSourceFilter(String authTokenHeaderName,String authToken){
        AUTH_TOKEN_HEADER_NAME=authTokenHeaderName;
        AUTH_TOKEN=authToken;
        LOGGER.info("Auth Token Header: "+AUTH_TOKEN_HEADER_NAME+", Auth Token: "+AUTH_TOKEN);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken=request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if(authToken==null || !authToken.equals(AUTH_TOKEN)){
            throw new ServletException("Unauthorized Access Source to the API Endpoint!");
        }
        filterChain.doFilter(request,response);
    }
}
