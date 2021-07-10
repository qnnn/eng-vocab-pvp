package com.arlin.controller;

import com.arlin.constants.StatusConst;
import com.arlin.dto.HistoryDTO;
import com.arlin.dto.Result;
import com.arlin.dto.WordDTO;
import com.arlin.entity.History;
import com.arlin.entity.Student;
import com.arlin.enums.TypeEnum;
import com.arlin.service.HistoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HistoryController
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@RestController
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public Result<List<HistoryDTO>> history(HttpSession session) {
        String sno = (String) session.getAttribute("Student");

        //数据库查找历史数据
        List<History> list = historyService.list(new LambdaQueryWrapper<History>()
                .select(History::getSno, History::getSName, History::getScore, History::getType, History::getCreateTime)
                .eq(History::getSno,sno));
        List<HistoryDTO> dtoList = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            HistoryDTO historyDTO = HistoryDTO.builder()
                    .sno(list.get(i).getSno())
                    .sName(list.get(i).getSName())
                    .type(TypeEnum.values()[list.get(i).getType() - 1].getName())
                    .score(list.get(i).getScore())
                    .date(DateFormatUtils.format(list.get(i).getCreateTime(), "yyyy-MM-dd HH:mm:ss"))
                    .build();
            dtoList.add(historyDTO);
        }
        return new Result<>(true, StatusConst.OK, "操作成功", dtoList);
    }
}
