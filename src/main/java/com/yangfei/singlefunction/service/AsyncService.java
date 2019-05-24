package com.yangfei.singlefunction.service;

import com.yangfei.singlefunction.entity.Course;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<List<Course>> getList1();
    CompletableFuture<Integer> getList1Count();
}
