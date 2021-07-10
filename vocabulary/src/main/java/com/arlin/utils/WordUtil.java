package com.arlin.utils;

import cn.hutool.core.util.RandomUtil;
import com.arlin.dto.CommitDTO;
import com.arlin.entity.CommitResult;
import com.arlin.enums.GradeEnum;
import com.arlin.enums.LimitEnum;
import com.arlin.enums.ScaleEnum;
import com.arlin.enums.TypeEnum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: WordUtil
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/24
 */
public class WordUtil {

    public static List<Integer> createWordIdList(Integer type, Integer[] nums) {
        List<Integer> ids = new ArrayList<>();
        Integer[] limitType = LimitEnum.values()[type - 1].getLimit();
        Integer[][] limitGrade = new Integer[nums.length][2];
        Integer len = (limitType[1] - limitType[0] + 1) / nums.length;
        limitGrade[0][0] = limitType[0];
        for(int i = 0; i < nums.length - 1; i++) {
            limitGrade[i][1] = limitGrade[i][0] + len;
            limitGrade[i + 1][0] = limitGrade[i][1] + 1;
        }
        limitGrade[4][1] = limitType[1];
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums[i]; j++) {
                ids.addAll(Collections.singleton(RandomUtil.randomInt(limitGrade[i][0], limitGrade[i][1])));
            }
        }
        return ids;
    }

    public static Integer calculateScore(CommitResult commitResult, Integer type) {

        Integer[] scale = ScaleEnum.values()[type - 1].getNums();
        float score = 0;
        //设置保留位数
        DecimalFormat df = new DecimalFormat("0.000");

        float[] rightRate = new float[5];
        Integer[] gradeCount = GradeEnum.values()[commitResult.getGrade() - 1].getNums();
        rightRate[0] = Float.valueOf(df.format((float)commitResult.getGradeCount1() / gradeCount[0]));
        rightRate[1] = Float.valueOf(df.format((float)commitResult.getGradeCount2() / gradeCount[1]));
        rightRate[2] = Float.valueOf(df.format((float)commitResult.getGradeCount3() / gradeCount[2]));
        rightRate[3] = Float.valueOf(df.format((float)commitResult.getGradeCount4() / gradeCount[3]));
        rightRate[4] = Float.valueOf(df.format((float)commitResult.getGradeCount5() / gradeCount[4]));

        //预估总词数
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                if (rightRate[i - 1] == 0) {
                    rightRate[i - 1] = 0.1f;
                }
                rightRate[i] *= rightRate[i - 1];
            }
//            score += rightRate[i] * scale[i];
            score += rightRate[i] * scale[i] * GradeEnum.values()[commitResult.getGrade() - 1].getPercentage();
        }
        return (int)score;
    }

    public static Integer calculateNextGrade(Integer type, Integer grade, Integer score) {
        Integer[] limit = LimitEnum.values()[type - 1].getLimit();
        Integer size = limit[1] - limit[0];
        Double percentage = score.doubleValue() / size;
        if(percentage > 0.8) {
            return grade + 1;
        } else if(percentage < 0.2){
            return grade - 1;
        }
        return grade;
    }
}
