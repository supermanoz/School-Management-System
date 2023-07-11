package com.sms.pojo.user_management;

import com.sms.enums.user_management.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePojo {

    private Long roleId;

    private UserEnum role;
}
