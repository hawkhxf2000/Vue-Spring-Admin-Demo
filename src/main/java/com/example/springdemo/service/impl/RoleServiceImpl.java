package com.example.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.entity.Role;
import com.example.springdemo.mapper.RoleMapper;
import com.example.springdemo.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author hawkh
* @description 针对表【t_role(User role assignment)】的数据库操作Service实现
* @createDate 2022-05-15 23:48:12
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




