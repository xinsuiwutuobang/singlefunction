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
public class Course implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 课程编号
     */
    @TableId("Cid")
    private Long Cid;

    /**
     * 课程名字
     */
    @TableField("Cname")
    private String Cname;

    /**
     * 教师编号
     */
    @TableField("Tid")
    private Long Tid;


}
