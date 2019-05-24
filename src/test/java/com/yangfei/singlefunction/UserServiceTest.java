package com.yangfei.singlefunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangfei.singlefunction.entity.User;
import com.yangfei.singlefunction.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>
 *  mybatis-plus api测试类
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserMapper mapper;
    /**
     * 插入
     */
    @Test
    public void aInsert() {
        User user = new User();
        user.setName("小明");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(user.getCreateDate());
        assertThat(mapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void bDelete() {
        assertThat(mapper.deleteById(3L)).isGreaterThan(0);
        assertThat(mapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getName, "Sandy"))).isGreaterThan(0);
    }
    /**
     * 更新
     */
    @Test
    public void cUpdate() {
        assertThat(mapper.updateById(new User().setId(1L).setEmail("ab@c.c"))).isGreaterThan(0);
        assertThat(
                mapper.update(
                        new User().setName("mp"),
                        Wrappers.<User>lambdaUpdate()
                                .set(User::getAge, 3)
                                .eq(User::getId, 2)
                )
        ).isGreaterThan(0);
        User user = mapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(3);
        assertThat(user.getName()).isEqualTo("mp");

        mapper.update(
                null,
                Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 2)
        );
        assertThat(mapper.selectById(1).getEmail()).isEqualTo("ab@c.c");
        user = mapper.selectById(2);
        assertThat(user.getEmail()).isNull();
        assertThat(user.getName()).isEqualTo("mp");

        mapper.update(
                new User().setEmail("miemie@baomidou.com"),
                new QueryWrapper<User>()
                        .lambda().eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        assertThat(user.getEmail()).isEqualTo("miemie@baomidou.com");

        mapper.update(
                new User().setEmail("miemie2@baomidou.com"),
                Wrappers.<User>lambdaUpdate()
                        .set(User::getAge, null)
                        .eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        assertThat(user.getEmail()).isEqualTo("miemie2@baomidou.com");
        assertThat(user.getAge()).isNull();
    }
    @Test
    public void dSelect() {
        mapper.insert(
                new User().setId(10086L)
                        .setName("miemie")
                        .setEmail("miemie@baomidou.com")
                        .setAge(3));
        assertThat(mapper.selectById(10086L).getEmail()).isEqualTo("miemie@baomidou.com");
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086));
        assertThat(user.getName()).isEqualTo("miemie");
        assertThat(user.getAge()).isEqualTo(3);

        mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId))
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNull();
                    assertThat(x.getAge()).isNull();
                });
        mapper.selectList(new QueryWrapper<User>().select("id","name"))
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNotNull();
                    assertThat(x.getAge()).isNull();
                });
    }
    @Test
    public void orderBy() {
        List<User> users = mapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        assertThat(users).isNotEmpty();
    }
    @Test
    public void selectMaps() {
        List<Map<String, Object>> mapList = mapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        assertThat(mapList).isNotEmpty();
        assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    @Test
    public void selectMapsPage() {
        IPage<Map<String, Object>> page = mapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        assertThat(page).isNotNull();
        assertThat(page.getRecords()).isNotEmpty();
        assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }

    @Test
    public void orderByLambda() {
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getAge));
        assertThat(users).isNotEmpty();
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = mapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }
}
