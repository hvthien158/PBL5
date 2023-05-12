package com.pbl5.PBL5_Elearning.service;

import com.pbl5.PBL5_Elearning.entity.Roles;
import com.pbl5.PBL5_Elearning.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceImp{

    @Autowired
    RolesRepository rolesRepository;
    @Override
    public Roles findByName(String name) {
        return rolesRepository.findFirstByRoleName(name).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
