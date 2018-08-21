package com.sicmed.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ScaleScoreUtils {

    public final static int[] answerList = {1, 1, 1, 1, 1, 1, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1,
            2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1,
            2, 3, 4, 5, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6};


    /**
     * 量表评估 获取所有评估得分
     *
     * @param scaleScore
     * @return
     */
    public static List<Integer> getScores(String scaleScore) {
        log.info("######量表评估 获取所有评估得分#####");
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
     * 判断疾病类型
     *
     * @param scoreList
     * @param type
     * @return
     */
    public static String checkIllnessType(List<Integer> scoreList, char type, Integer patientAge) {

        int result = 0;
        List<Integer> newScoreList = new LinkedList<>();
        switch (type) {
            case 0:
                newScoreList.addAll(scoreList.subList(0, 22));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result > 0 && patientAge < 40) {
                    //前列腺炎
                    return checkDegree(scoreList, '0');
                } else if (result == 0 && patientAge < 40) {
                    checkIllnessType(scoreList, '1', patientAge);
                } else if (result > 0 && patientAge > 39) {
                    checkIllnessType(scoreList, '2', patientAge);
                } else if (result == 0 && patientAge > 39) {
                    checkIllnessType(scoreList, '3', patientAge);
                }
                break;
            case 1:
                newScoreList.addAll(scoreList.subList(22, 64));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //正常
                    return "正常";
                } else if (result > 0) {
                    //前列腺增生
                    return checkDegree(scoreList, '1');
                }
                break;
            case 2:
                newScoreList.addAll(scoreList.subList(22, 64));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //不确定
                    return "不确定";
                } else if (result > 0) {
                    //前列腺炎
                    return checkDegree(scoreList, '0');
                }
                break;
            case 3:
                newScoreList.addAll(scoreList.subList(22, 64));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result == 0) {
                    //正常
                    return "正常";
                } else if (result > 0) {
                    //前列腺增生
                    return checkDegree(scoreList, '1');
                }
                break;
            default:
                break;
        }
        return "不确定";
    }


    /**
     * 病情严重程度查询
     *
     * @param scoreList
     * @param type      0:前列腺炎 ;1:前列腺增生
     * @return
     */
    private static String checkDegree(List<Integer> scoreList, char type) {
        int result = 0;
        List<Integer> newScoreList = new LinkedList<>();
        switch (type) {
            case 0:
                newScoreList.addAll(scoreList.subList(0, 34));
                newScoreList.addAll(scoreList.subList(65, 79));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result < 15) {
                    //轻度
                    return "轻度前列腺炎";
                } else if (result > 29) {
                    //重度
                    return "重度前列腺炎";
                } else {
                    //中度
                    return "中度前列腺炎";
                }
            case 1:
                newScoreList.addAll(scoreList.subList(23, 64));
                for (Integer num : newScoreList) {
                    result += num;
                }
                if (result < 8) {
                    //轻度
                    return "轻度前列腺增生";
                } else if (result > 19) {
                    //重度
                    return "重度前列腺增生";
                } else {
                    //中度
                    return "中度前列腺增生";
                }
            default:
                break;
        }
        return "不确定";
    }


    public static String getOptionScore(List<Integer> scoreList) {
        log.info("获取所有题目得分结果>>>>"+scoreList.size());
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

        conScoreList = scoreList.subList(35, 41);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(41, 47);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(47, 53);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(53, 59);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(59, 65);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(65, 69);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(69, 73);
        scores.append(sumList(conScoreList));
        scores.append("-");

        conScoreList = scoreList.subList(73, 80);
        scores.append(sumList(conScoreList));

        return scores.toString();
    }

    private static int sumList(List<Integer> conScoreList){
        int result = 0;

        for (Integer num : conScoreList) {
            result += num;
        }
        return result;
    }
}
