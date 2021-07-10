package com.arlin.utils;

import com.arlin.dto.CommitDTO;
import com.arlin.entity.CommitResult;

/**
 * @ClassName: CommitUtil
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
public class CommitUtil {

    /**
     * 计算各个级别的词汇量
     * @param commitDTO
     * @return
     */
    public static CommitResult calculateResult(CommitDTO commitDTO) {
        CommitResult commitResult = new CommitResult(0,0,0,0,0,0);
        commitResult.setGrade(commitDTO.getGrade());
        int size = commitDTO.getWords().size();
        for(int i = 0; i < size; i++) {
            switch (commitDTO.getWordDTOS().get(i).getGrade()) {
                case 1:
                    commitResult.setGradeCount1(commitResult.getGradeCount1() + 1);break;
                case 2:
                    commitResult.setGradeCount2(commitResult.getGradeCount2() + 1);break;
                case 3:
                    commitResult.setGradeCount3(commitResult.getGradeCount3() + 1);break;
                case 4:
                    commitResult.setGradeCount4(commitResult.getGradeCount4() + 1);break;
                case 5:
                    commitResult.setGradeCount5(commitResult.getGradeCount5() + 1);break;
            }
        }
        return commitResult;
    }
}
