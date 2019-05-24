package com.yangfei.singlefunction.controller.Async;

import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.service.AsyncService;
import com.yangfei.singlefunction.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private AsyncService asyncService;

    /**
     * 异步调用
     * @return
     */
    @RequestMapping("/completableFuture")
    public Object completableFuture() {
        log.info("异步调用多次db，归约每次的返回值");
        Integer ret = 0;
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<Integer>> counts = new ArrayList<CompletableFuture<Integer>>();
        IntStream.rangeClosed(0, 9).boxed().forEach(n -> {
            CompletableFuture<Integer> count = asyncService.getList1Count();
            counts.add(count);
        });
        CompletableFuture[] completableFutures = counts.stream().toArray(CompletableFuture[]::new);
        //CompletableFuture<Integer> target[]  = (CompletableFuture<Integer>[]) counts.toArray();
        CompletableFuture.allOf(completableFutures).join();
        try {
            for (CompletableFuture<Integer> completableFuture : completableFutures) {
                ret += completableFuture.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info(String.format("异步调用花费时间：%d",endTime - startTime));
        return String.format(ret + "异步调用花费时间：%d",System.currentTimeMillis() - startTime);
    }

    @RequestMapping("/sync")
    public Object snyc(){
        log.info("同步调用多次db，归约每次的返回值");
        List<Integer> ret = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        IntStream.rangeClosed(0, 9).boxed().forEach(n -> {
            Integer count = iCourseService.count(null);
            ret.add(count);
        });
        long endTime = System.currentTimeMillis();
        log.info(String.format("同步调用花费时间：%d",endTime - startTime));
        return String.format(ret.stream().mapToInt(Integer::intValue).sum() + "异步调用花费时间：%d",System.currentTimeMillis() - startTime);
    }
}
