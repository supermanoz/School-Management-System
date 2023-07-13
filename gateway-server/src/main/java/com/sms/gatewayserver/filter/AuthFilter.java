package com.sms.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class AuthFilter {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static Logger LOGGER= LoggerFactory.getLogger(AuthFilter.class);

    @Value("${sms.auth.token.header.name}")
    private String AUTH_TOKEN_HEADER_NAME;

    @Value("${sms.auth.token.value}")
    private String AUTH_TOKEN_HEADER_VALUE;

    @Bean
    public GlobalFilter globalFilter() {
//        return (exchange, chain) -> {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", exchange.getRequest().getHeaders().getFirst("Authorization"));
//            headers.add(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN_HEADER_VALUE);
//            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
//            SmsResponse res = restTemplate.exchange("http://localhost:8091/api/auth/authenticate", HttpMethod.GET, entity, SmsResponse.class).getBody();
//            ObjectMapper mapper = new ObjectMapper();
//            Boolean valid = mapper.convertValue(res.getPayload(), Boolean.class);
//            LOGGER.trace("Pre filter:- the validity of authentication token is " + valid);
//            if (!valid) {
//                return exchange.getResponse().setComplete();
//            }
//            return chain.filter(exchange);
//        };
        return (exchange, chain) ->{
            return webClientBuilder.build().get()
                    .uri("http://AUTH-SERVICE/api/auth/authenticate")
                    .headers(header -> {
                        header.set(AUTH_TOKEN_HEADER_NAME,AUTH_TOKEN_HEADER_VALUE);
                        header.set("Authorization", exchange.getRequest().getHeaders().getFirst("Authorization"));
                    })
                    .exchange()
                    .flatMap(response->response.statusCode().value()==200?chain.filter(exchange):setModifiedResponse(exchange));
        };
    }

    private Mono<Void> setModifiedResponse(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        LOGGER.trace("Setting response as unauthorized");
        return exchange.getResponse().setComplete();
    }
}
