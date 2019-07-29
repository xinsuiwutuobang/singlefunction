package com.yangfei.singlefunction.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Component
@ConfigurationProperties(prefix = "base.admin")
@Data
public class BaseAdminConstant {
    private String name;
    private Byte age;
    private String phone;
    private Byte sex;
}
