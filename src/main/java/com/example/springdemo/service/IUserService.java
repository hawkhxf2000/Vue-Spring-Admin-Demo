package com.example.springdemo.service;

import com.example.springdemo.controller.DTO.UserStatDTO;
import com.example.springdemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springdemo.controller.DTO.UserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄晓峰
 * @since 2022-03-31
 */
public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    UserStatDTO getUserCountByMonth();
}
