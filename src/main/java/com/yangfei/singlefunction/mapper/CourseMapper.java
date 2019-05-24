package com.yangfei.singlefunction.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public interface CourseMapper extends BaseMapper<Course> {

    Course selectByFirst();

    IPage<Course> selectCoursePage(Page page, @Param("name") String name);
}
