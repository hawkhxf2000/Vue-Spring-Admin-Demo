package com.example.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hawkh
* @description 针对表【t_role(User role assignment)】的数据库操作Mapper
* @createDate 2022-05-15 23:48:12
* @Entity generator.domain.Role
*/

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




