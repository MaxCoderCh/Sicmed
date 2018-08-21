package com.sicmed.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class BloodRoutineUtils {

    public final static int[] answerList = {1, 0, -1};

    /**
     * 血常规 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getBloodRoutineScores(String bloodRoutineAnswer) {
        log.info("######获取血常规评估状态列表#####");
        if (answerList.length != bloodRoutineAnswer.length()) {
            return null;
        }
        int answers[] = new int[bloodRoutineAnswer.length()];
        for (int i = 0; i < bloodRoutineAnswer.length(); i++) {
            char c = bloodRoutineAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> bloodRoutineList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    bloodRoutineList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return bloodRoutineList;
    }

    /**
     * 血常规 解读
     *
     * @return
     */


}
