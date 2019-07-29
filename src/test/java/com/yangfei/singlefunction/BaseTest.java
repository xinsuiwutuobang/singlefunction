package com.yangfei.singlefunction;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest {
    @Before
    public void testBefore() {
        log.info("--------------------------start--------------------------");
    }

    @After
    public void testAfter() {
        log.info("---------------------------end--------------------------");

    }

    int[] baseArray = {8, 2, 5, 7, 6, 9,3, 4, 1, 0};

}
