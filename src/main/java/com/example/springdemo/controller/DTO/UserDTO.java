package com.example.springdemo.controller.DTO;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String token;
    private String avatar;
}
