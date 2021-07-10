package com.arlin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.arlin.dao.WordDao;
import com.arlin.domain.EasyWord;
import com.arlin.domain.ExcelData;
import com.arlin.domain.ExcelRandom;
import com.arlin.entity.CommitResult;
import com.arlin.entity.Student;
import com.arlin.entity.Word;
import com.arlin.enums.GradeEnum;
import com.arlin.enums.TestEnum;
import com.arlin.enums.TypeEnum;
import com.arlin.handler.ModelExcelListener;
import com.arlin.entity.WordExcel;
import com.arlin.service.WordService;
import com.arlin.utils.MapperUtils;
import com.arlin.utils.WordUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class VocabularyApplicationTests {

    @Autowired
    WordService wordService;

    @Autowired
    WordDao wordDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("excel/ky.xlsx");
        InputStream inputStream = classPathResource.getInputStream();
        List<WordExcel> list = new ArrayList<>();
        list = EasyExcel.read(inputStream, WordExcel.class, new ModelExcelListener()).sheet().doReadSync();
    }

    @Test
    void test() throws IOException {
        int[] num = new int[]{748, 748, 748, 748, 749};
        int flag = 0;
        for (int i = 0; i < num.length; i++) {
            List<Word> words = wordDao.selectList(new LambdaQueryWrapper<Word>()
                    .select(Word::getWord, Word::getDefinition)
                    .eq(Word::getType, 1)
                    .gt(Word::getId, flag)
                    .last("limit " + num[i]));
            flag += num[i];
            for (Word word : words) {
                EasyWord easyWord = new EasyWord(word.getWord(), word.getDefinition());
                try {
                    String json = MapperUtils.obj2json(easyWord);
                    redisTemplate.opsForSet().add(String.format("word_cet4: level-%d", i), json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void test11() throws IOException {
        int[] num = new int[]{748, 748, 748, 748, 749};
        int flag = 0;
        for (int i = 0; i < num.length; i++) {
            List<Word> words = wordDao.selectList(new LambdaQueryWrapper<Word>()
                    .select(Word::getDefinition)
                    .eq(Word::getType, 1)
                    .gt(Word::getId, flag)
                    .last("limit " + num[i]));
            flag += num[i];
            for (Word word : words) {
                try {
                    redisTemplate.opsForSet().add("word_cet4: chinese", word.getDefinition());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void main() {
        List list = redisTemplate.opsForSet().randomMembers("word_cet4: level-1", 2);
        for (Object o : list) {
            try {
                EasyWord word = MapperUtils.json2pojo(o.toString(), EasyWord.class);
                System.out.println(word.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void test2() {
        CommitResult gradeCountDTO = new CommitResult();
        gradeCountDTO.setGrade(3);
        gradeCountDTO.setGradeCount1(10);
        gradeCountDTO.setGradeCount2(12);
        gradeCountDTO.setGradeCount3(16);
        gradeCountDTO.setGradeCount4(8);
        gradeCountDTO.setGradeCount5(4);

        Integer score = WordUtil.calculateScore(gradeCountDTO, 1);
        System.out.println("测试结果为：" + score);
        System.out.println("下一级：" + WordUtil.calculateNextGrade(1, 3, score));
    }

    @Test
    void test3() {
        String fileName = "C:\\Users\\Arlin\\Desktop\\data1.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet;
        for (int i = 0; i < 3; i++) {
            writeSheet = EasyExcel.writerSheet(i, TypeEnum.values()[i].getName()).head(ExcelData.class).build();
            excelWriter.write(data(i + 1), writeSheet);
        }
        excelWriter.finish();
    }

    List<ExcelData> data(int type) {
        List<ExcelData> excelDataList = new ArrayList<>();
        for (int i = 0; i < 126; i++) {
            ExcelData excelData = new ExcelData();
            excelData.setType(type);
            int grade = 3;
            Double[] ratio = TestEnum.values()[i].getRatio();
            excelData.setRatio1(ratio[0]);
            excelData.setRatio2(ratio[1]);
            excelData.setRatio3(ratio[2]);
            excelData.setRatio4(ratio[3]);
            excelData.setRatio5(ratio[4]);

            for (int j = 0; j < 3; j++) {
                Integer[] nums = GradeEnum.values()[grade - 1].getNums();

                CommitResult gradeCountDTO = new CommitResult();
                gradeCountDTO.setGrade(grade);
                gradeCountDTO.setGradeCount1((int) Math.round((nums[0] * ratio[0])));
                gradeCountDTO.setGradeCount2((int) Math.round((nums[1] * ratio[1])));
                gradeCountDTO.setGradeCount3((int) Math.round((nums[2] * ratio[2])));
                gradeCountDTO.setGradeCount4((int) Math.round((nums[3] * ratio[3])));
                gradeCountDTO.setGradeCount5((int) Math.round((nums[4] * ratio[4])));

                Integer score = WordUtil.calculateScore(gradeCountDTO, type);
                if (j == 0) {
                    excelData.setGrade1(grade);
                    excelData.setScore1(score);
                } else if (j == 1) {
                    excelData.setGrade2(grade);
                    excelData.setScore2(score);
                } else {
                    excelData.setGrade3(grade);
                    excelData.setScore3(score);
                }
                grade = WordUtil.calculateNextGrade(type, grade, score);
            }
            excelDataList.add(excelData);
        }
        return excelDataList;
    }

    @Test
    void test4() {
        int type = 1;
        String fileName = "C:\\Users\\Arlin\\Desktop\\data_random_1.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();

        for (int i = 0; i < 5; i++) {
            List<ExcelRandom> list = new ArrayList<>();
            WriteSheet writeSheet = EasyExcel.writerSheet(i, String.valueOf(i + 1)).head(ExcelRandom.class).build();
            for (int j = 0; j < 100; j++) {
                ExcelRandom excelRandom = new ExcelRandom();

                double ratio5 = new Random().nextDouble();
                double ratio4 = 0.2 + new Random().nextDouble() * 0.8;
                double ratio3 = 0.4 + new Random().nextDouble() * 0.6;
                double ratio2 = 0.6 + new Random().nextDouble() * 0.4;
                double ratio1 = 0.8 + new Random().nextDouble() * 0.2;

                excelRandom.setRatio1(ratio1);
                excelRandom.setRatio2(ratio2);
                excelRandom.setRatio3(ratio3);
                excelRandom.setRatio4(ratio4);
                excelRandom.setRatio5(ratio5);

                Integer[] nums = GradeEnum.values()[i].getNums();
                CommitResult gradeCountDTO = new CommitResult();
                gradeCountDTO.setGrade(i + 1);
                gradeCountDTO.setGradeCount1((int) Math.round((nums[0] * ratio1)));
                gradeCountDTO.setGradeCount2((int) Math.round((nums[1] * ratio2)));
                gradeCountDTO.setGradeCount3((int) Math.round((nums[2] * ratio3)));
                gradeCountDTO.setGradeCount4((int) Math.round((nums[3] * ratio4)));
                gradeCountDTO.setGradeCount5((int) Math.round((nums[4] * ratio5)));
                Integer score = WordUtil.calculateScore(gradeCountDTO, type);
                excelRandom.setScore(score);
                list.add(excelRandom);
            }
            excelWriter.write(list, writeSheet);
        }
        excelWriter.finish();
    }

    @Test
    void test5() {
        int type = 1;
        String fileName = "C:\\Users\\Arlin\\Desktop\\data_random_1.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();

        for (int i = 1; i <= 10; i++) {
            List<ExcelRandom> list = new ArrayList<>();
            WriteSheet writeSheet = EasyExcel.writerSheet(i, String.valueOf(i)).head(ExcelRandom.class).build();
            for (int j = 0; j < 100; j++) {
                ExcelRandom excelRandom = new ExcelRandom();

                double ratio = Math.min(1, (i * 0.1 - 0.05) + new Random().nextDouble() * 0.1);

                excelRandom.setRatio1(ratio);
                excelRandom.setRatio2(ratio);
                excelRandom.setRatio3(ratio);
                excelRandom.setRatio4(ratio);
                excelRandom.setRatio5(ratio);

                Integer[] nums = GradeEnum.values()[1].getNums();
                CommitResult gradeCountDTO = new CommitResult();
                gradeCountDTO.setGrade(2);
                gradeCountDTO.setGradeCount1((int) Math.round((nums[0] * ratio)));
                gradeCountDTO.setGradeCount2((int) Math.round((nums[1] * ratio)));
                gradeCountDTO.setGradeCount3((int) Math.round((nums[2] * ratio)));
                gradeCountDTO.setGradeCount4((int) Math.round((nums[3] * ratio)));
                gradeCountDTO.setGradeCount5((int) Math.round((nums[4] * ratio)));
                Integer score = WordUtil.calculateScore(gradeCountDTO, type);
                excelRandom.setScore(score);
                list.add(excelRandom);
            }
            excelWriter.write(list, writeSheet);
        }
        excelWriter.finish();
    }
}
