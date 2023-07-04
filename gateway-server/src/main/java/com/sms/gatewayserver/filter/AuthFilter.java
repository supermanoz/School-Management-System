package com.sms.gatewayserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.gatewayserver.config.SmsRestTemplate;
import com.sms.gatewayserver.response.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthFilter {

    @Autowired
    private RestTemplate restTemplate;

    private static Logger LOGGER= LoggerFactory.getLogger(AuthFilter.class);

    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", exchange.getRequest().getHeaders().getFirst("Authorization"));
            headers.add("SMS_API_KEY","thisisasecret");
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            SmsResponse res = restTemplate.exchange("http://localhost:8091/api/auth/authenticate", HttpMethod.GET, entity, SmsResponse.class).getBody();
            ObjectMapper mapper = new ObjectMapper();
            Boolean valid = mapper.convertValue(res.getPayload(), Boolean.class);
            LOGGER.info("Pre filter:- the validity of authentication token is " + valid);
            if (!valid) {
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }
}
