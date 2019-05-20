package com.yangfei.singlefunction.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Sc implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 学号
     */
    @TableId("Sid")
    private Long Sid;

    /**
     * 课程编号
     */
    @TableField("Cid")
    private Long Cid;

    /**
     * 成绩
     */
    private Integer score;


}
