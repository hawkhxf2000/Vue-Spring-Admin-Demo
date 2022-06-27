package com.example.springdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.entity.Menu;
import com.example.springdemo.entity.Role;
import com.example.springdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/save")
    public boolean save(@RequestBody Menu menu) {
        return menuService.saveOrUpdate(menu);
    }

    @GetMapping("/page")
    public IPage<Menu> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name) {
        IPage<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");
        return menuService.page(page, queryWrapper);
    }

    @DeleteMapping("deletebyid/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return menuService.removeById(id);
    }

    @PostMapping("/deletebatchids")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return menuService.removeByIds(ids);
    }

    @GetMapping("/{id}")
    public Menu findOne(@PathVariable Integer id) {
        return menuService.getById(id);
    }

}
