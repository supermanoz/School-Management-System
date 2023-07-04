package com.sms.gatewayserver.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsResponse{
    private String message;
    private Boolean status;
    private Object payload;
}
