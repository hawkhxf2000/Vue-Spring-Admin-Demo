package com.example.springdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.common.Constants;
import com.example.springdemo.controller.DTO.UserStatDTO;
import com.example.springdemo.entity.User;
import com.example.springdemo.controller.DTO.UserDTO;
import com.example.springdemo.exception.ServiceException;
import com.example.springdemo.mapper.UserMapper;
import com.example.springdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 黄晓峰
 * @since 2022-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();


    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public UserStatDTO getUserCountByMonth() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("year(create_time) as year", "month(create_time) as month","count(id) as count");
        queryWrapper.groupBy("month");
        queryWrapper.orderByAsc("year","month");
        List<Map<String, Object>> maps = listMaps(queryWrapper);
        ArrayList<String> xAxis = new ArrayList();
        ArrayList<Long> yAxis = new ArrayList();
        maps.forEach(obj->{
            xAxis.add(obj.get("year") + "-" +obj.get("month"));
            yAxis.add((Long)obj.get("count"));
        });
        UserStatDTO userStatDTO = new UserStatDTO();
        userStatDTO.setXAxis(xAxis);
        userStatDTO.setYAxis(yAxis);
        return userStatDTO;
    }

//    @Override
//    public UserStatDTO getUserCount(UserStatDTO userStatDTO) {
//
//        return userStatDTO;
//    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

}