package com.example.springdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.entity.User;
import com.example.springdemo.mapper.UserMapper;
import com.example.springdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;


    @Test
    public void testSelectById() {
        User user = userMapper.selectById("1");
    }

    @Test
    public void testBatchInsert() {
        for (int i = 0; i < 365; i++) {
            User tempUser =  new User();
            tempUser.setUsername("hhh"+(10+i));
            tempUser.setPassword("123456");
            tempUser.setEmail("hhh"+i+"@gg.com");
            tempUser.setCreateTime(LocalDateTime.now().minusDays(i));

            userService.save(tempUser);
        }
    }

    @Test
    public void testCOuntByMonth() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("year(create_time) as year", "month(create_time) as month","count(id) as count");
        queryWrapper.groupBy("month");
        queryWrapper.orderByAsc("year","month");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        ArrayList<String> xAxis = new ArrayList();
        ArrayList<Object> yAxis = new ArrayList();
        maps.forEach(obj->{
            xAxis.add(obj.get("year") + "-" +obj.get("month"));
            yAxis.add(obj.get("count"));
        });
//       Map<String,List>  map = new HashMap<>();
//       map.put("xAxis", xAxis);
//       map.put("yAxis", yAxis);
//        System.out.println(map.toString());
    }
}
