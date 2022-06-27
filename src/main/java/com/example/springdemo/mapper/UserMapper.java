package com.example.springdemo.mapper;

import com.example.springdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄晓峰
 * @since 2022-03-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
