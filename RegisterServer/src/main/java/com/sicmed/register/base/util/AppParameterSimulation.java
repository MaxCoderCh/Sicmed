package com.sicmed.register.base.util;

import org.springframework.expression.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: ykbian
 * @Date: 2018/9/4 11:15
 * @Todo:  参数验证类
 */

public class AppParameterSimulation {

    public final static String isPinYin = "[a-zA-Z]+"; // 是否为拼音
    public final static String isChinese = "[\\u4e00-\\u9fa5]+";// 是否为汉字
    public final static String isNumber = "[0-9]+"; // 数字规则
    public final static String isEntityNumber = "[0-9a-zA-Z]+"; // 数字规则
    public final static String isEntityName = "[a-zA-Z\\u4e00-\\u9fa5]+";// 是否为特殊名称(ex:CR颈部检测)
    public final static String isTelephone = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"; // 是否是移动电话

//    public final static String isIdCard18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$"; // 是否为ID
    public final static String isIDCard18 = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
    // Card
    // 18位
    public final static String isIdCard15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"; // 是否为ID
    // Card
    // 15位
    public final static String isUUId = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$"; // UUID正则
    public final static Integer askCodeLength = 8;
    public final static Integer askNamePYLength = 15;
    public final static Integer askNameLength = 20;
    public final static Integer registeredStatusLength = 20; // 挂号类型最大长度
    public final static Integer pricStatusLength = 1; // 挂号缴费状态长度
    public final static Integer isPayLength = 1; // 缴费状态长度
    public final static Integer payClassTypeLength = 1; // 缴费类型长度
    public final static Integer patientNameLength = 20; // 患者名称
    public final static Integer patientIDCardLength = 18; // 患者身份证
    public final static Integer patientTelephoneLength = 14; // 患者移动电话
    public final static Integer patientNumberLength = 10; // 患者编号长度
    public final static Integer entrustedAskedLength = 200; // 嘱托医嘱长度
    public final static Integer diseaseNameLength = 20; // 嘱托医嘱长度
    public final static Integer diseaseNamePYLength = 10; // 嘱托医嘱长度
    public final static Integer diseaseCodeLength = 200; // 嘱托医嘱长度

    /*
     * 验证ID id均为UUID 需要为32位
     */
    public static Boolean simulationId(String id) {
        // 验证参数不为空 且 不是注入攻击
        if (MedicalParameter.DetectionParameter(id)) {
            if (id.length() == 32 || id.matches(isUUId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 验证名字是字母和数字组合
     */
    public static Boolean simulationName(String name) {
        // 验证名称为空或者是否为字母和汉字
        if (MedicalParameter.DetectionParameter(name)) {
            if (name.matches(isEntityNumber) ) {
                return true;
            }
        }
        return false;
    }


    /**
     * 验证AskedClass编号长度是否为1位
     *
     */
    public static Boolean AskedClassId(String id) {
        // 验证医嘱类别编号不等于空而且长度等于1
        if (MedicalParameter.DetectionParameter(id)) {
            if (id.length() == 1 || id.matches(isNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证asked编号长度是否为8位
     */
    public static Boolean AskedId(String id) {
        // 验证医嘱编号不等于空而且长度等于8位
        if (MedicalParameter.DetectionParameter(id)) {
            if (id.length() == 8 || id.matches(isEntityNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证人数不大于4位数
     */
    public static Boolean Number(String number) {
        // 验证人数大于4位数
        if (number.length() < 4 || number.matches(isNumber)) {
            return true;
        }
        return false;
    }

    /**
     * 验证科室编号等于4位
     */
    public static Boolean BranchCode(String code) {
        // 验证科室编号是否为空,是否等于4位
        if (MedicalParameter.DetectionParameter(code)) {
            if (code.length() == 4 || code.matches(isNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证部门拼音简写小于4位
     */
    public static Boolean DeptPmpa(String deptPmpa) {
        // 判断部门拼音简写不为空,长度不大于4
        if (MedicalParameter.DetectionParameter(deptPmpa)) {
            if (deptPmpa.length() < 5 || deptPmpa.matches(isPinYin)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证科室名称长度小于30
     */
    public static Boolean BranchName(String name) {
        // 验证科室名称不为空,长度小于30
        if (MedicalParameter.DetectionParameter(name)) {
            if (name.length() < 31 || name.matches(isEntityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证英文名长度小于50
     */
    public static Boolean BranchEnglist(String name) {
        // 验证科室英文名不为空,长度小于50
        if (MedicalParameter.DetectionParameter(name)) {
            if (name.length() < 51 || name.matches(isPinYin)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 验证地理位置长度小于 60
     */
    public static Boolean Location(String location) {
        // 验证科室位置名称长度小于60
        if (MedicalParameter.DetectionParameter(location)) {
            if (location.length() < 61) {
                return true;
            }
        }
        return false;
    }

    /*
     * 验证参数一长度 是否相等参数二
     *
     * 参数一 不为空
     *
     * return true 参数一不为空或者参数一等于参数二长度 return false 参数为空或者参数一的长度与参数二长度不相等
     */
    public static Boolean simulationEqualLength(String parameter, Integer length) {

        // 验证参数不为空 且 不是注入攻击
        if (MedicalParameter.DetectionParameter(parameter)) {
            if (parameter.length() == length) {
                return true;
            }
        }

        return false;
    }

    /*
     * 验证参数一长度 是否小于参数二 参数一 不为空
     *
     * return true 参数一不为空或者参数一小于参数二长度 return false 参数为空或者参数一的长度与参数二长度不相等
     */
    public static Boolean simulationLength(String parameter, Integer length) {

        if (MedicalParameter.DetectionParameter(parameter)) {
            if (parameter.length() < length) {
                return true;
            }
        }

        return false;
    }

    /*
     * 如果是拼音返回true 否则返回false
     */
    public static Boolean simulationIsPinYin(String parameter) {

        if (MedicalParameter.DetectionParameter(parameter)) {
            return parameter.matches(isPinYin) ? true : false;
        }
        return false;
    }

    /*
     * 如果是汉字返回true 否则返回false
     */
    public static Boolean simulationIsChinese(String parameter) {

        if (MedicalParameter.DetectionParameter(parameter)) {
            return parameter.matches(isChinese) ? true : false;
        }
        return false;

    }

    /*
     * 如果是数字返回true 否则返回false
     */
    public static Boolean simulationIsNumber(String parameter) {

        if (MedicalParameter.DetectionParameter(parameter)) {
            return parameter.matches(isNumber) ? true : false;
        }
        return false;

    }

    /*
     * 如果是特殊名称(英文+汉字 ex:CR颈部检测) 返回true 否则返回false
     */
    public static Boolean simulationIsEntityName(String parameter) {

        if (MedicalParameter.DetectionParameter(parameter)) {
            return parameter.matches(isEntityName) ? true : false;
        }
        return false;

    }

    /*
     * 医嘱编码 长度为8 组合为拼音+数字
     */
    public static Boolean askCode(String parameter) {

        if (isEqualLength(parameter, askCodeLength, isEntityNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 医嘱名字长度为1-20 组合为拼音+数字
     */
    public static Boolean askName(String parameter) {

        if (isBetweenLength(parameter, 0, askNameLength, isEntityName)) {
            return true;
        }
        return false;
    }

    /*
     * 拼音长度 长度为1-10 组合为拼音+数字
     */
    public static Boolean askNamePY(String parameter) {

        if (isBetweenLength(parameter, 0, askNamePYLength, isPinYin)) {
            return true;
        }
        return false;
    }

    /*
     * 挂号缴费状态 长度为1 数字
     */
    public static Boolean pricStatus(String parameter) {

        if (isEqualLength(parameter, pricStatusLength, isNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 缴费状态 长度为20 数字
     */
    public static Boolean registeredStatus(String parameter) {

        if (isBetweenLength(parameter, 0, registeredStatusLength, isChinese)) {
            return true;
        }
        return false;
    }

    /*
     * 挂号缴费状态 长度为1 数字
     */
    public static Boolean isPay(String parameter) {

        if (isEqualLength(parameter, isPayLength, isNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 缴费类型 长度为1 数字
     */
    public static Boolean payClassType(String parameter) {

        if (isEqualLength(parameter, payClassTypeLength, isNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 患者名称长度 长度为1-20 组合为汉字
     */
    public static Boolean patientName(String parameter) {

        if (isBetweenLength(parameter, 0, patientNameLength, isChinese)) {
            return true;
        }
        return false;
    }

	/*
	 * 身份证长度 长度为1-18 组合为数字
	 */
    // public static Boolean patientIDCard(String parameter) {
    //
    // if (isEqualLength(parameter, patientIDCardLength, isNumber)) {
    // return true;
    // }
    // return false;
    // }

    /*
     * 患者电话长度 长度为1-14 组合为数字
     */
    public static Boolean patientTel(String parameter) {

        if (isEqualLength(parameter, patientTelephoneLength, isNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 患者编号长度 长度为1-10 组合为数字
     */
    public static Boolean patientNumber(String parameter) {

        if (isBetweenLength(parameter, 0, patientNumberLength, isNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 医嘱编号长度 长度为1-10 组合为数字
     */
    public static Boolean entrustedAsked(String parameter) {

        if (isBetweenLength(parameter, 0, entrustedAskedLength, isEntityName)) {
            return true;
        }
        return false;
    }

    /*
     * 疾病名称长度
     */
    public static Boolean diseaseName(String parameter) {

        if (isBetweenLength(parameter, 0, diseaseNameLength, isEntityName)) {
            return true;
        }
        return false;
    }

    /*
     * 疾病拼音长度
     */
    public static Boolean diseaseNamePY(String parameter) {

        if (isBetweenLength(parameter, 0, diseaseNamePYLength, isPinYin)) {
            return true;
        }
        return false;
    }

    /*
     * 疾病编号
     */
    public static Boolean diseaseCode(String parameter) {

        if (isBetweenLength(parameter, 0, diseaseCodeLength, isEntityNumber)) {
            return true;
        }
        return false;
    }

    /*
     * 判断参数一长度是否等于参数二 并且符合参数三所约束的格式
     */
    public static Boolean isEqualLength(String parameter, Integer length, String regs) {

        // 验证参数不为空 且 不是注入攻击
        if (MedicalParameter.DetectionParameter(parameter)) {
            if (parameter.length() == length || parameter.matches(regs)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 判断参数一 在参数二和参数三范围内 并且参数格式符合参数四约束
     */
    public static Boolean isBetweenLength(String parameter, Integer minLength, Integer maxLength, String regs) {

        // 验证参数不为空 且 不是注入攻击
        if (MedicalParameter.DetectionParameter(parameter)) {
            if (parameter.length() > minLength || parameter.length() < maxLength || parameter.matches(regs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为浮点数或者整数
     *
     * @author Max
     * @param str
     * @return true Or false
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为正确的邮件格式
     *
     * @author Max
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str))
            return false;
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * 判断字符串是否为合法手机号 11位 13 14 15 18开头
     *
     * @author Max
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str) {
        if (isEmpty(str)){
            return false;
        }

        return str.matches("^(13|14|15|16|17|18|19)\\d{9}$");
    }

    /**
     * 判断是否为数字
     *
     * @author Max
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 判断字符串是否为非空(包含null与"")
     *
     * @author Max
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str == null || "".equals(str))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为非空(包含null与"","    ")
     *
     * @author Max
     * @param str
     * @return
     */
    public static boolean isNotEmptyIgnoreBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为空(包含null与"")
     *
     * @author Max
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str))
            return true;
        return false;
    }

    /**
     * 判断字符串是否为空(包含null与"","    ")
     *
     * @author Max
     * @param str
     * @return
     */
    public static boolean isEmptyIgnoreBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }

    /**
     * 判断用户输入的时间格式是否正确
     */
    public static boolean checkDateTime(String inputDate) {
        String DATE_TIME_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.CHINA);
        simpleDateFormat.setLenient(false);
        boolean check = false;
        try {
            simpleDateFormat.parse(inputDate);
            check = true;
        } catch (Exception ex) {
            check = false;
            ex.printStackTrace();
        }

        return check;
    }

    /**
     * 此方法判断输入字符是否为数字0-9 是返回true不是返回false
     *
     * @author Max
     * @param c
     *            char
     * @return boolean
     */
    public static boolean isDigit(char c) {
        return (('0' <= c) && (c <= '9'));
    }

    public static boolean isDigit(String inputStr) {
        char tempChar;
        for (int i = 0; i < inputStr.length(); i++) {
            tempChar = inputStr.charAt(i);
            // 如果字符中有一个字符不是数字则返回false
            if (!isDigit(tempChar)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 此方法判断输入字符是否为字母a-z或A-Z 是返回true不是返回false
     *
     * @author Max
     * @param c
     *            char
     * @return boolean
     */
    public static boolean isAlpha(char c) {
        return ((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')));
    }

    public static boolean isAlpha(String inputStr) {
        char tempChar;
        for (int i = 0; i < inputStr.length(); i++) {
            tempChar = inputStr.charAt(i);
            if (!isAlpha(tempChar)) { // 如果字符中有一个字符不是字母则返回false
                return false;
            }
        }

        return true;
    }

    /**
     * 此方法用于检查密码或用户名是否合法，用户名密码只能使用英文字母、数字以及-和_，并且首字符必须为字母或数字 密码首字符必须为字母或数字
     *
     * @author Max
     * @param inputStr
     *            输入
     * @return boolean
     */
    public static boolean checkUserNamePassword(String inputStr) {
        for (int nIndex = 0; nIndex < inputStr.length(); nIndex++) {
            char cCheck = inputStr.charAt(nIndex);
            if (nIndex == 0 && (cCheck == '-' || cCheck == '_')) {
                return false;
            }
            if (!(isDigit(cCheck) || isAlpha(cCheck) || cCheck == '-' || cCheck == '_')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 此方法检查email有效性 返回提示信息
     *
     * @author Max
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        // 电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();

        return isMatched;
    }

    /**
     *
     *
     * @author Max
     * @param name
     *            中文姓名
     * @return boolean
     */
    public static boolean isChineseName(String name) {
        if (!name.matches("[\u4e00-\u9fa5]{1,6}")) {
            return false;
        } else{
            return true;
        }

    }

    /**
     * 此方法检查输入的身份证号有效性 返回提示信息 如果返回布尔类型则通过验证
     *
     * @author Max
     * @param IDNumber
     *            身份证号
     * @return String
     */
    public static boolean checkIDNumber(String IDNumber) {
        boolean result = IDNumber.matches("[0-9]{15}|[0-9]{17}[0-9X]");
        if (result) {
            int year, month, date;
            if (IDNumber.length() == 15) {
                year = Integer.parseInt(IDNumber.substring(6, 8));
                month = Integer.parseInt(IDNumber.substring(8, 10));
                date = Integer.parseInt(IDNumber.substring(10, 12));
            } else {
                year = Integer.parseInt(IDNumber.substring(6, 10));
                month = Integer.parseInt(IDNumber.substring(10, 12));
                date = Integer.parseInt(IDNumber.substring(12, 14));
            }
            switch (month) {
                case 2:
                    result = (date >= 1) && (year % 4 == 0 ? date <= 29 : date <= 28);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    result = (date >= 1) && (date <= 31);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    result = (date >= 1) && (date <= 31);
                    break;
                default:
                    result = false;
                    break;
            }
        }
        return result;
    }

    /**
     * 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，
     * 三位数字顺序码和一位数字校验码。
     *
     * 2、地址码(前六位数） 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
     *
     * 3、出生日期码（第七位至十四位） 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
     *
     * 4、顺序码（第十五位至十七位）
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
     *
     * 5、校验码（第十八位数） （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, , 16
     * ，先对前17位数字的权求和 Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6
     * 3 7 9 10 5 8 4 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7
     * 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     *
     */

    /**
     * 判断手机号码是否合法
     *
     * @author Max
     * @param handset
     *            手机号
     * @return 是否合法
     */
    public static boolean isHandset(String handset) {
        try {
            if (!handset.substring(0, 1).equals("1")) {
                return false;
            }
            if (handset == null || handset.length() != 11) {
                return false;
            }
            // String check = "^[0123456789]+$";
            String check = "^1[3-9]\\d{9}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(handset);

            return matcher.matches();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断大陆地区固话及小灵通 区号：010,020,021,022,023,024,025,027,028,029
     *
     * @author Max
     * @param tel
     *            电话号码
     * @return 是否合法
     */
    public static boolean isTel(String tel) {
        try {
            String check = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(tel);

            return matcher.matches();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证是否包含中文
     *
     * @author Max
     * @param str
     * @return 是否包含中文:含有中文-true，没有中文-false
     */
    public static boolean containChinese(String str) {
        // String check = "^[\u4e00-\u9fa5]{1,}$";

        String check = "^[\\u4e00-\\u9fa5]+?";

        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);

        return matcher.find();
    }

    /**
     * 验证是否包含空格
     *
     * @author Max
     * @param str
     * @return 是否包含空格
     */
    public static boolean containBlank(String str) {
        if (str.length() > str.replace(" ", "").length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证用户名是否只含中英文和数字
     *
     * @author Max
     * @param userName
     *            用户名
     * @return 是否合法
     */
    public static boolean isUserName(String userName) {
        String check = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(userName);

        return matcher.matches();
    }

    /**
     * 身份证的有效验证
     *
     * @author Max
     * @param IDStr身份证号
     * @return 有效：true 无效：false
     * @throws ParseException
     */
    public static boolean IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        // String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
        String Ai = "";

        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "号码长度应该为15位或18位。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份

        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "生日无效。";
            System.out.println(errorInfo);
            return false;
        }

        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "生日不在有效范围。";
                System.out.println(errorInfo);
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        } catch (java.text.ParseException e) {
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "月份无效";
            System.out.println(errorInfo);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "日期无效";
            System.out.println(errorInfo);
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码是否有效 ================
        Hashtable<String, String> h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "地区编码错误。";
            System.out.println(errorInfo);
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，最后一位字母错误";
                System.out.println(errorInfo);
                return false;
            }
        } else {
            System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
            System.out.println("新身份证号:" + Ai);
            return true;
        }
        // =====================(end)=====================
        System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
        return true;
    }

    /**
     * ======================================================================
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    public static Hashtable<String, String> GetAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @author Max
     * @param strDate
     *            字符串
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * ======================================================================
     * =功能:在判定已经是正确的身份证号码之后,查找出身份证所在地区
     *
     * @author Max
     * @param idCard
     *            身份证号码
     * @return 所在地区
     */
    public static String GetArea(String idCard) {
        Hashtable<String, String> ht = GetAreaCode();
        String area = ht.get(idCard.substring(0, 2));
        return area;
    }

    /**
     * ======================================================================
     * =功能:在判定已经是正确的身份证号码之后,查找出此人性别
     *
     * @author Max
     * @param idCard
     *            身份证号码
     * @return 男或者女
     */
    public static String GetSex(String idCard) {
        String sex = "";
        if (idCard.length() == 15){
            sex = idCard.substring(idCard.length() - 3, idCard.length());
        }


        if (idCard.length() == 18){
            sex = idCard.substring(idCard.length() - 4, idCard.length() - 1);
        }

        System.out.println(sex);
        int sexNum = Integer.parseInt(sex) % 2;
        if (sexNum == 0) {
            return "女";
        }
        return "男";
    }

    /**
     * ======================================================================
     * =功能:在判定已经是正确的身份证号码之后,查找出此人出生日期
     *
     * @author Max
     * @param idCard
     *            身份证号码
     * @return 出生日期 XXXX MM-DD
     */

    public static String GetBirthday(String idCard) {
        String Ain = "";
        if (idCard.length() == 18) {
            Ain = idCard.substring(0, 17);
        } else if (idCard.length() == 15) {
            Ain = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
        }

        // ================ 出生年月是否有效 ================
        String strYear = Ain.substring(6, 10);// 年份
        String strMonth = Ain.substring(10, 12);// 月份
        String strDay = Ain.substring(12, 14);// 日期
        return strYear + "-" + strMonth + "-" + strDay;
    }

}
