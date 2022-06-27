package com.example.springdemo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.entity.Role;
import com.example.springdemo.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/save")
    public boolean save(@RequestBody Role role) {
        return roleService.saveOrUpdate(role);
    }

    @GetMapping("/page")
    public IPage<Role> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String description) {
        IPage<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(description)) {
            queryWrapper.like("description", description);
        }
        queryWrapper.orderByDesc("id");
        return roleService.page(page, queryWrapper);
    }

    @DeleteMapping("deletebyid/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return roleService.removeById(id);
    }

    @PostMapping("/deletebatchids")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return roleService.removeByIds(ids);
    }

    @GetMapping("/{id}")
    public Role findOne(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Role> list = roleService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("nickname", "昵称");
//        writer.addHeaderAlias("email", "邮箱");
//        writer.addHeaderAlias("phone", "电话");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("角色信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        //获取输出流
        ServletOutputStream out = response.getOutputStream();
        //将writer中的内容刷新到输出流中
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Role> list = reader.readAll(Role.class);
        System.out.println(list);
        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<User> users = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//                User user = new User();
//                user.setUsername(row.get(0).toString());
//                user.setPassword(row.get(1).toString());
//                user.setNickname(row.get(2).toString());
//                user.setEmail(row.get(3).toString());
//                user.setPhone(row.get(4).toString());
//                user.setAddress(row.get(5).toString());
////                user.setAvatarUrl(row.get(6).toString());
//                users.add(user);
//        }

        roleService.saveBatch(list);
        return true;
    }
}
