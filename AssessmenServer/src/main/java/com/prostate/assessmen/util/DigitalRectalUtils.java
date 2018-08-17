package com.prostate.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class DigitalRectalUtils {
    public final static int[] answerList = {0, 1, 11, 1, 11, 111, 1, 0, 1, 0};
    /**
     * 前列腺指诊 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getDigitalRectalList(String digitalRectalAnswer) {

        log.info("######获取前列腺指诊评估状态列表#####");
        if (answerList.length != digitalRectalAnswer.length()) {
            return null;
        }
        int answers[] = new int[digitalRectalAnswer.length()];
        for (int i = 0; i < digitalRectalAnswer.length(); i++) {
            char c = digitalRectalAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> digitalRectalList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    digitalRectalList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return digitalRectalList;
    }
}
