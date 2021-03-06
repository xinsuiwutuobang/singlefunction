package com.yangfei.singlefunction.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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


    public static void main(String[] args) {
        String aa = "fjiapfjafjoiasjfio32i4ji2o34jo";
        int i = aa.hashCode();
        String aaa = "fjiapfjfio32i4ji2o34jo";
        int ia = aaa.hashCode();
        System.out.println(i);
        System.out.println(ia);

    }
}
