package com.yangfei.singlefunction;

import com.yangfei.singlefunction.properties.BaseAdminConstant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class PropertiesTest extends BaseTest {
    @Autowired
    private BaseAdminConstant baseAdminConstant;
    @Test
    public void adminTest() {
        System.out.println(baseAdminConstant.toString());
    }
}
