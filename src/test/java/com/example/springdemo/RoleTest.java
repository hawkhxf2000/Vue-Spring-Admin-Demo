package com.example.springdemo;

import com.example.springdemo.entity.Role;
import com.example.springdemo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTest {
    @Autowired
    RoleService roleService;

    @Test
    public void insertRole() {
        Role role = new Role();
        role.setName("admin");
        role.setDescription("Administrator");
        roleService.save(role);
    }

    @Test
    public void selectAll() {
        roleService.list();
    }
}
