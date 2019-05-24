package com.yangfei.singlefunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangfei.singlefunction.entity.Course;
import com.yangfei.singlefunction.mapper.CourseMapper;
import com.yangfei.singlefunction.service.ICourseService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleFunctionApplicationTests {
    @Resource
    private CourseMapper mapper;
    @Resource
    private ICourseService iCourseService;
    @Test
    public void aInsert() {
        Course entity = new Course();
        entity.setTid(2L).setCname(String.valueOf(System.currentTimeMillis()));
        Assertions.assertThat(mapper.insert(entity))
        .isGreaterThan(0);
        Assertions.assertThat(entity.getTid()).isNotNull();
    }

    /**
     * lambdaUpdate().set() 覆盖对象中的值，可以设置null
     */
    @Test
    public void bUpdate() {
        mapper.update(new Course().setCname("update"),
                Wrappers.<Course>lambdaUpdate().set(Course::getCname, null)
                        .set(Course::getTid,2L)
                        .eq(Course::getCid, 1L));
    }

    @Test
    public void cListObjs() {
        Function<Course, String> fu = (Course c) -> c.getCname();
        //CourseService.listObjs(fu);
        List<Object> collect = mapper.selectObjs(new QueryWrapper<Course>().last(" limit 10")).stream().filter(n -> n != null)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
