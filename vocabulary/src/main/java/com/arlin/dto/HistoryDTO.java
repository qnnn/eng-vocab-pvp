package com.arlin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HistoryDTO
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {

    private Long sno;
    private String sName;
    private String type;
    private Integer score;
    private String date;
}
