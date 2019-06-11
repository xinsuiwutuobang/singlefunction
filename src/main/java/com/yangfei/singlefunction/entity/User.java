package com.yangfei.singlefunction.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer age;

    private String name;

    private String email;


}
