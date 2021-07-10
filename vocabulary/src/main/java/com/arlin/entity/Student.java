package com.arlin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Student
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_student")
public class Student {

    @TableId(value = "sno", type = IdType.AUTO)
    private Long sno;
    private String sName;
    private String password;
}
