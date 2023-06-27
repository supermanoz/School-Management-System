package com.sms.pojo;

import com.sms.enums.user_management.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {

    private Long userId;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @Size(min = 8, max = 40)
    private String password;


    private Long roleId;

}
