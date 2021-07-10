package com.arlin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Word
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_word")
public class Word {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer sort;
    private String word;
    private Integer collins;
    private String definition;
    private Integer type;
    private Integer grade;
}
