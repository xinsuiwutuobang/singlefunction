package com.yangfei.singlefunction.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.mapper.CourseMapper;
import com.yangfei.singlefunction.service.ICourseService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;
    @RequestMapping("/test")
    public Object test(){
        int count = iCourseService.count();
        return count;
    }

    /**
     * mybatis-plus 自定义mapper.xml分页
     * @param name
     * @param current
     * @param size
     * @return
     */
    @RequestMapping("/selectCoursePage")
    public Object selectCoursePage(String name, Integer current, Integer size) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> ret = iCourseService.selectCoursePage(page, name);
        return ret;
    }

    @RequestMapping("/coursePage")
    public Object coursePage(Integer current, Integer size) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> ret = iCourseService.page(page, new LambdaQueryWrapper<Course>().orderByDesc(Course::getCid));
        return ret;
    }

    @RequestMapping("/insert")
    public Object insert() {
        Course course = new Course();
        Random random = new Random(100000);
        course.setCname("course" + random.nextLong());
        course.setTid(1L);
        return iCourseService.save(course);
    }

    @RequestMapping("/test1")
    public Object testqw() {
        LambdaQueryWrapper<Course> lqw =new LambdaQueryWrapper<Course>();
        QueryWrapper<Course> qw = new QueryWrapper<Course>();
        return null;
    }

    public static void main(String[] args) {

    }
}

