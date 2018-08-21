package com.sicmed.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UrineFlowRateUtils {

    public final static int[] answerList = {1, 0, -1};

    /**
     * 尿流率 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getUrineFlowRateList(String urineFlowRateAnswer) {

        log.info("######获取尿流率状态列表#####");
        if (answerList.length != urineFlowRateAnswer.length()) {
            return null;
        }
        int answers[] = new int[urineFlowRateAnswer.length()];
        for (int i = 0; i < urineFlowRateAnswer.length(); i++) {
            char c = urineFlowRateAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> urineFlowRateList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    urineFlowRateList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        return urineFlowRateList;
    }
}
