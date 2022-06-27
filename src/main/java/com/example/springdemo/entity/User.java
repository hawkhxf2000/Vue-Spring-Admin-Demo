package com.example.springdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黄晓峰
 * @since 2022-03-31
 */
@Getter
@Setter
  @TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("username")
      private String username;

      @ApiModelProperty("password")
      private String password;

    private String nickname;

    private String email;

    private String phone;

    private String address;

    private LocalDateTime createTime;

    @TableField(value = "avatar_url")  // 指定数据库的字段名称
    private String avatar;

}
