package com.sms.pojo.user_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsPojo {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String authorities;
}
