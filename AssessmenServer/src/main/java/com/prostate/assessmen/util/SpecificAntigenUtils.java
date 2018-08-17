package com.prostate.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SpecificAntigenUtils {

    public final static int[] answerList = {1, 1, 0, -1, 1, 0, -1, 1, 0, -1};

    /**
     * 特异性抗原 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getSpecificAntigenList(String specificAntigenAnswer) {

        log.info("######获取特异性抗原评估状态列表#####");
        if (answerList.length != specificAntigenAnswer.length()) {
            return null;
        }
        int answers[] = new int[specificAntigenAnswer.length()];
        for (int i = 0; i < specificAntigenAnswer.length(); i++) {
            char c = specificAntigenAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> specificAntigenList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    specificAntigenList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return specificAntigenList;
    }
}
