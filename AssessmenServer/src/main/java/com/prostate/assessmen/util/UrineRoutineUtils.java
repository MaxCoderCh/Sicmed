package com.prostate.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UrineRoutineUtils {

    public final static int[] answerList = {1, 0, -1};

    /**
     * 尿常规 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getUrineRoutineList(String urineRoutineAnswer) {
        log.info("######获取尿常规状态列表#####");

        if (answerList.length != urineRoutineAnswer.length()) {
            return null;
        }
        int answers[] = new int[urineRoutineAnswer.length()];
        for (int i = 0; i < urineRoutineAnswer.length(); i++) {
            char c = urineRoutineAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> urineRoutineList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    urineRoutineList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return urineRoutineList;
    }
}
