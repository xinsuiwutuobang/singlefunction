package com.yangfei.singlefunction.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.mapper.CourseMapper;
import com.yangfei.singlefunction.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
        QueryWrapper<Course> qw = new QueryWrapper<>();
        if (true) {
            qw.eq("age", "xiaoming");
        }
        qw.orderByAsc("create_time");
        List<Course> list = iCourseService.list(new QueryWrapper<Course>().lambda().eq(Course::getCname, "A").eq(Course::getTid, 2L).or().or().eq(Course::getCname, "A").eq(Course::getTid, 2L));

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
        IPage<Course> ret = iCourseService.page(page, new LambdaQueryWrapper<Course>().eq(Course::getCname,"xiaoming").orderByDesc(Course::getCid));
        return ret;
    }

    @RequestMapping("/insert")
    public Object insert() {
        IntStream.rangeClosed(0,100000).boxed().forEach(n -> {
            Course course = new Course();
            course.setCname("courseData" + System.currentTimeMillis());
            course.setTid(1L);
            iCourseService.save(course);
        });
        return null;
    }

    @RequestMapping("/selectList")
    public Object selectList() {
        List<Course> list = iCourseService.list(Wrappers.<Course>lambdaQuery().select(Course::getCid,Course::getTid).last(" limit 10"));
        return list;
    }
    @RequestMapping("/insertBatch")
    public Object insertBatch() {
        IntStream.rangeClosed(0,1000).boxed().forEach(m -> {
            List<Course> list = new ArrayList<>();
            IntStream.rangeClosed(0,1000).boxed().forEach(n -> {
                Course course = new Course();
                course.setCname("courseData" + System.currentTimeMillis());
                course.setTid(1L);
                list.add(course);
            });
            iCourseService.saveBatch(list);
        });
        return null;
    }

    @RequestMapping("/test1")
    public Object testqw() {
        LambdaQueryWrapper<Course> lqw =new LambdaQueryWrapper<>();
        QueryWrapper<Course> qw = new QueryWrapper<>();
        Function<Course,String> mapper = n -> n.getCname();
        iCourseService.listObjs(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) {
                Course entity = (Course) o;
                return entity.getCname();
            }
        });
        //List<String> vs = iCourseService.listObjs(new QueryWrapper<Course>(), mapper);
        return null;
    }

    public static void main(String[] args) {
        //此时，所有的Object对象都没有被释放，因为变量v引用这些对象。
        //如果对象加入到 Vector 后，还必须从 Vector 中删除，最简单的方法就是将 Vector 对象设置为 null。
        int times = 10;
        Vector<Object> vertor = new Vector<>(times);
        LongStream.rangeClosed(0, times).boxed().forEach(n -> {
            Object o = new Object();
            vertor.add(o);
            o = null;
        });

    }
}

