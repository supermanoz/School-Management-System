//package com.sms.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthorizationFilter extends OncePerRequestFilter {
//
//    private static final Logger LOGGER= LoggerFactory.getLogger(AuthorizationFilter.class);
//    private String AUTH_TOKEN_HEADER_NAME;
//    private String AUTH_TOKEN;
//
//    public AuthorizationFilter(){}
//
//    public AuthorizationFilter(String AUTH_TOKEN_HEADER_NAME, String AUTH_TOKEN) {
//        this.AUTH_TOKEN_HEADER_NAME = AUTH_TOKEN_HEADER_NAME;
//        this.AUTH_TOKEN = AUTH_TOKEN;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        return (exchange, chain) ->{
//            return WebClient.builder().build().get()
//                    .uri("http://AUTH-SERVICE/api/auth/authenticate")
//                    .headers(header -> {
//                        header.set(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN);
//                        header.set("Authorization", request.getHeader("Authorization"));
//                    })
//                    .exchange()
//                    .flatMap(respond->respond.statusCode().value()==200?setAuthorizedResponse(exchange,chain):setUnauthorizedResponse(exchange));
//        };
//        filterChain.doFilter(request,response);
//    }
//
//    private Mono<Void> setAuthorizedResponse(ServerWebExchange exchange, GatewayFilterChain chain){
//        HttpHeaders headers=HttpHeaders.writableHttpHeaders(exchange.getRequest().getHeaders());
//        headers.set(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN_HEADER_VALUE);
//        LOGGER.trace("Setting api auth header to authorized request");
//        return chain.filter(exchange);
//    }
//
//    private Mono<Void> setUnauthorizedResponse(HttpServletResponse response){
//        response.setStatus(401);
//        LOGGER.trace("Setting response as unauthorized");
//        return response.
//    }
//}
