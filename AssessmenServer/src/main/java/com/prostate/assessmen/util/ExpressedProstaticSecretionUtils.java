package com.prostate.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExpressedProstaticSecretionUtils {
    public final static int[] answerList = {1, 0, -1, 1, 0, -1};

    /**
     * 前列腺按摩液检查 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getExpressedProstaticSecretionList(String expressedProstaticSecretionAnswer) {

        log.info("######获取前列腺按摩液检查评估状态列表#####");
        if (answerList.length != expressedProstaticSecretionAnswer.length()) {
            return null;
        }
        int answers[] = new int[expressedProstaticSecretionAnswer.length()];
        for (int i = 0; i < expressedProstaticSecretionAnswer.length(); i++) {
            char c = expressedProstaticSecretionAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> expressedProstaticSecretionList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    expressedProstaticSecretionList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return expressedProstaticSecretionList;
    }
}
