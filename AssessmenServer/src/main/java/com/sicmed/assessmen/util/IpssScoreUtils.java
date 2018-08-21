package com.sicmed.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class IpssScoreUtils {

    public final static int[] answerList = {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5,
            0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5,
            0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 6};


    /**
     * 量表评估 获取所有评估得分
     *
     * @param scaleScore
     * @return
     */
    public static List<Integer> getScores(String scaleScore) {
        log.info("######IPSS 获取所有评估得分#####");
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
        List<Integer> newScoreList = new LinkedList<>();
        newScoreList.addAll(scoreList.subList(0, 42));
        for (Integer num : newScoreList) {
            result += num;
        }
        if (result < 8) {
            //轻度
            return "轻度症状";
        } else if (result > 7 && result < 20) {
            //重度
            return "中度症状";
        } else if (result > 19 && result < 36) {
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


        conScoreList = scoreList.subList(0, 6);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(6, 12);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(12, 18);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(18, 24);
        scores.append(sumList(conScoreList));
        scores.append("-");
        newScoreList.add(sumList(conScoreList));

        conScoreList = scoreList.subList(24, 30);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(30, 36);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(36, 42);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(42, 49);
        scores.append(sumList(conScoreList));

        return scores.toString();
    }


    /**
     * 计算症状评分
     *
     * @param answer
     * @return
     */
    public static String countSymptomScore(String answer) {
        List<Integer> scores = getOptionScoreInt(answer);
        List<Integer> conScoreList = scores.subList(0, 7);

        Integer score = sumList(conScoreList);
        return score.toString();
    }

    /**
     * 计算生活质量评分
     *
     * @param answer
     * @return
     */
    public static String countLifeScore(String answer) {
        List<Integer> scores = getOptionScoreInt(answer);
        List<Integer> conScoreList = scores.subList(7, 8);

        Integer score = sumList(conScoreList);

        return score.toString();
    }

    private static int sumList(List<Integer> conScoreList) {
        int result = 0;

        for (Integer num : conScoreList) {
            result += num;
        }
        return result;
    }

    /**
     * 获取 题目得分 结果集
     *
     * @param answer
     * @return
     */
    private static List<Integer> getOptionScoreInt(String answer) {
        List<Integer> scoreList = getScores(answer);
        List<Integer> newScoreList = new LinkedList<>();

        newScoreList.add(sumList(scoreList.subList(0, 6)));
        newScoreList.add(sumList(scoreList.subList(6, 12)));
        newScoreList.add(sumList(scoreList.subList(12, 18)));
        newScoreList.add(sumList(scoreList.subList(18, 24)));
        newScoreList.add(sumList(scoreList.subList(24, 30)));
        newScoreList.add(sumList(scoreList.subList(30, 36)));
        newScoreList.add(sumList(scoreList.subList(36, 42)));
        newScoreList.add(sumList(scoreList.subList(42, 49)));

        return newScoreList;
    }
}
