package com.example.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.entity.Menu;
import com.example.springdemo.service.MenuService;
import com.example.springdemo.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author hawkh
* @description 针对表【t_menu】的数据库操作Service实现
* @createDate 2022-05-24 10:27:54
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




