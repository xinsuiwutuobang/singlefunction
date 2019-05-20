package com.yangfei.singlefunction.service.impl;

import com.yangfei.singlefunction.entity.Student;
import com.yangfei.singlefunction.mapper.StudentMapper;
import com.yangfei.singlefunction.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
