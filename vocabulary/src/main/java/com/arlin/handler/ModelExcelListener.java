package com.arlin.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.arlin.entity.Word;
import com.arlin.entity.WordExcel;
import com.arlin.service.WordService;
import com.arlin.utils.SpringUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ModelExcelListener
 * @Description: Excel读取数据监听处理
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Component
public class ModelExcelListener extends AnalysisEventListener {

    List<Word> dataList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     * @param data
     * @param context
     */
    @Override
    public void invoke(Object data, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        WordExcel wordExcel = (WordExcel) data;
        if(wordExcel.getWord() == null || wordExcel.getWord().length() == 0) return;
        Word word = new Word();
        word.setSort(Integer.parseInt(wordExcel.getRank()));
        word.setWord(wordExcel.getWord());
        if(wordExcel.getCollins() == null) {
            word.setCollins(0);
        } else {
            word.setCollins(Integer.parseInt(wordExcel.getCollins()));
        }
        word.setDefinition(wordExcel.getDefinition());
        word.setType(3);
        dataList.add(word);
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成");

        WordService wordService = (WordService) SpringUtil.getBean(Class.forName("com.arlin.service.WordService"));
        for (Word word : dataList) {
            System.out.println(word);
//            wordService.saveCET4(word);
        }
        wordService.saveBatch(dataList);
    }
}

