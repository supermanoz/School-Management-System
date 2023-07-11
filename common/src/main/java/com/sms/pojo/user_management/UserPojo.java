package com.sms.pojo.user_management;

import com.sms.enums.user_management.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPojo {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private UserEnum role;
}
