package com.yangfei.singlefunction.service.impl;

import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.mapper.CourseMapper;
import com.yangfei.singlefunction.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    @Async
    public CompletableFuture<List<Course>> getList1() {
        List<Course> ret = courseMapper.selectList(null);
        return CompletableFuture.completedFuture(ret);
    }
    @Async
    @Override
    public CompletableFuture<Integer> getList1Count() {
        Integer ret = courseMapper.selectCount(null);
        return CompletableFuture.completedFuture(ret);
    }

}
