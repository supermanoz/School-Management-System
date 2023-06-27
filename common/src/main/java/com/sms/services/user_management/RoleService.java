package com.sms.services.user_management;

import com.sms.model.user_management.Role;

import java.util.List;

public interface RoleService {

    Role findById(Long id);

    List<Role> findAll();
}
