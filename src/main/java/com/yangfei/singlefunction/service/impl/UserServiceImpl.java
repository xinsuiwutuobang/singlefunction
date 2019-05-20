package com.yangfei.singlefunction.service.impl;

import com.yangfei.singlefunction.entity.User;
import com.yangfei.singlefunction.mapper.UserMapper;
import com.yangfei.singlefunction.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
