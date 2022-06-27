package com.example.springdemo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.example.springdemo.common.Result;
import com.example.springdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    IUserService userService;

    @GetMapping("/getdata")
    public Result getData(){
        Map<String, Object> map = new HashMap<>();
        map.put("x", new String[]{"Shirts", "Cardigans", "Chiffons", "Pants", "Heels", "Socks"});
        map.put("y", new int[]{5, 20, 36, 10, 10, 20});
        return Result.success(map);
    }

    @GetMapping("/getUserCountByMonth")
    public Result getUserCountByMonth(){
        return Result.success(userService.getUserCountByMonth());
    }
}
