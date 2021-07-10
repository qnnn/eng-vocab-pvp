package com.arlin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: History
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_history")
public class History {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Long sno;
    private String sName;
    private Integer type;
    private Integer score;
    private Date createTime;
}
