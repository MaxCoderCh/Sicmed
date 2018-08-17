package com.prostate.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class NihCpsiScoreUtils {

    public final static int[] answerList = {1, 1, 1, 1, 1, 1, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6};


    /**
     * 量表评估 获取所有评估得分
     *
     * @param scaleScore
     * @return
     */
    public static List<Integer> getScores(String scaleScore) {
        log.info("######NIH-CPSI 获取所有评估得分#####");
        log.info("######基础答案库 大小#####" + answerList.length);
        log.info("######前台答案 长度#####" + scaleScore.length());
        int ia[] = new int[scaleScore.length()];
        for (int i = 0; i < scaleScore.length(); i++) {
            char c = scaleScore.charAt(i);//逐个获取字符串中的字符
            ia[i] = (int) (c - '0');
        }
//        char[] scaleScores = scaleScore.toCharArray();
        List<Integer> scoreList = new LinkedList();
        log.info(ia.toString());
        int i = 0;
        for (int score : ia) {
            switch (score) {
                case 1:
                    scoreList.add(answerList[i]);
                    break;
                case 0:
                    scoreList.add(0);
                    break;
            }
            i++;
        }
        log.info(scoreList.toString());
        log.info("i=" + i);
        return scoreList;
    }

    /**
     * 病情严重程度查询
     *
     * @param scoreList
     * @return
     */
    public static String checkDegree(List<Integer> scoreList) {
        int result = 0;

        for (Integer num : scoreList) {
            result += num;
        }
        if (result > 0 && result < 15) {
            //轻度
            return "轻度症状";
        } else if (result > 14 && result < 30) {
            //重度
            return "中度症状";
        } else if (result > 29 && result < 44) {
            //中度
            return "重度症状";
        }
        return "无症状";
    }


    public static String getOptionScore(List<Integer> scoreList) {
        log.info("获取所有题目得分结果>>>>" + scoreList.size());
        List<Integer> newScoreList = new LinkedList<>();
        List<Integer> conScoreList;
        StringBuffer scores = new StringBuffer();


        conScoreList = scoreList.subList(0, 1);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(1, 2);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(2, 3);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(3, 4);
        scores.append(sumList(conScoreList));
        scores.append("-");
        newScoreList.add(sumList(conScoreList));

        conScoreList = scoreList.subList(4, 5);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(5, 6);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(6, 12);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(12, 23);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(23, 29);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(29, 35);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(35, 39);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(39, 43);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(43, 50);
        scores.append(sumList(conScoreList));

        return scores.toString();
    }
    public static String countInflammationScore(String answer) {
        List<Integer> scores = getScores(answer);
        Integer score = sumList(scores.subList(0,28));

        return score.toString();
    }

    public static String countLifeScore(String answer) {
        List<Integer> scores = getScores(answer);
        Integer score = sumList(scores.subList(28,38));

        return score.toString();
    }

    public static String countSymptomScore(String answer) {
        List<Integer> scores = getScores(answer);

        Integer score = sumList(scores.subList(38,50));

        return score.toString();
    }

    private static int sumList(List<Integer> conScoreList) {
        int result = 0;

        for (Integer num : conScoreList) {
            result += num;
        }
        return result;
    }
}
