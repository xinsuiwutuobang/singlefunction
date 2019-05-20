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
public class Teacher implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 教师编号
     */
    @TableId("Tid")
    private Long Tid;

    /**
     * 教师名字
     */
    @TableField("Tname")
    private String Tname;


}
