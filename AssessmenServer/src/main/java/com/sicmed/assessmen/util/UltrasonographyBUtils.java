package com.sicmed.assessmen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UltrasonographyBUtils {

    public final static int[] answerList = {1, 0, -1, 0, 1, 11, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1};

    /**
     * B超 检验项 状态获取
     *
     * @return
     */
    public static List<Integer> getUltrasonographyBList(String ultrasonographyBAnswer) {

        log.info("######获取B超状态列表#####");
        if (answerList.length != ultrasonographyBAnswer.length()) {
            return null;
        }
        int answers[] = new int[ultrasonographyBAnswer.length()];
        for (int i = 0; i < ultrasonographyBAnswer.length(); i++) {
            char c = ultrasonographyBAnswer.charAt(i);
            //逐个获取字符串中的字符
            answers[i] = c - '0';
        }
        List<Integer> ultrasonographyBList = new LinkedList();
        int i = 0;
        for (int answer : answers) {
            switch (answer) {
                case 1:
                    ultrasonographyBList.add(answerList[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        log.info(ultrasonographyBList.toString());
        return ultrasonographyBList;
    }
}
