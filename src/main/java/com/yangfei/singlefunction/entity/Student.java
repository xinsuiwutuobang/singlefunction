package com.yangfei.singlefunction.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class Student implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 学号
     */
    @TableId("Sid")
    private Long Sid;

    /**
     * 学生姓名
     */
    @TableField("Sname")
    private String Sname;

    /**
     * 学生年龄
     */
    @TableField("Sage")
    private Integer Sage;

    /**
     * 学生性别
     */
    @TableField("Ssex")
    private String Ssex;


}
