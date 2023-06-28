package com.sms.services.user_management.serviceImpl;

import com.sms.repository.user_management.RoleRepository;
import com.sms.model.user_management.Role;
import com.sms.services.user_management.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
