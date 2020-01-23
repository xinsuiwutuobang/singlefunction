package com.yangfei.singlefunction.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Teacher;
import com.yangfei.singlefunction.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;
    @RequestMapping("/add")
    public Object add() {
        Teacher entity = new Teacher();
        entity.setTid(new Random().nextLong());
        entity.setTname(String.valueOf(new Random().nextLong()));
        boolean save = iTeacherService.save(entity);
        return save;
    }

    @RequestMapping("/update")
    public Object update(Long id) {
        Teacher entity = new Teacher();
        entity.setTid(id);
        entity.setTname(String.valueOf(new Random().nextLong()));
        boolean update = iTeacherService.updateById(entity);
        return update;
    }

    @RequestMapping("/selectById")
    public Object selectById(Long id) {
        return iTeacherService.getById(id);
    }

    @RequestMapping("/list")
    public Object list() {
        IPage<Teacher> page = new Page<>(0, 10);
        return iTeacherService.page(page);
    }
}

