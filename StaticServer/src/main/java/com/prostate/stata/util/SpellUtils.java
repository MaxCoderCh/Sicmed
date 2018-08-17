package com.prostate.stata.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *
 */
@Slf4j
public class SpellUtils {

    /**
     * 根据汉字得到对应的拼音
     * @param
     * @return
     */

    public static String getPinYin(String source) {
        char[] t1 = source.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder result = new StringBuilder();
        for (char aT1 : t1) {
            if (Character.toString(aT1).matches("[\\u4E00-\\u9FA5]")) {
                try {
                    result.append(PinyinHelper.toHanyuPinyinStringArray(aT1, format)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error("转换汉字拼音错误!", e);
                }
            } else {
                // 非汉字不进行转换，直接添加
                result.append(aT1);
            }
        }
        return result.toString();
    }

    /**
     * 得到汉字的首字母
     * @param source
     * @return
     */
    public static String getPinYinHeaderChar(String source) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char word = source.charAt(i);
            if (Character.toString(word).matches("[\\u4E00-\\u9FA5]")) {
                String[] pinYinArr = PinyinHelper.toHanyuPinyinStringArray(word);
                result.append(pinYinArr[0].charAt(0));
            } else {
                // 非汉字不进行转换，直接添加
                result.append(word);
            }
        }
        return result.toString();
    }

}
