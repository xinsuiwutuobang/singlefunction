package com.yangfei.singlefunction.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.mapper.CourseMapper;
import com.yangfei.singlefunction.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * mybatis-plus 自定义分页
     * @param page
     * @param name
     * @return
     */
    @Override
    public IPage<Course> selectCoursePage(Page<Course> page,String name) {
        return courseMapper.selectCoursePage(page,name);
    }
}
