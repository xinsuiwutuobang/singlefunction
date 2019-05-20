package com.yangfei.singlefunction.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public interface ICourseService extends IService<Course> {
    IPage<Course> selectCoursePage(Page<Course> page, String name);
}
