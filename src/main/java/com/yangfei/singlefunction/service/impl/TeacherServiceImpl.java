package com.yangfei.singlefunction.service.impl;

import com.yangfei.singlefunction.entity.Teacher;
import com.yangfei.singlefunction.mapper.TeacherMapper;
import com.yangfei.singlefunction.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
