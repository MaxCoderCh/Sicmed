package com.sicmed.register.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 创建者:丁学志
 * 
 * 解决参数注入问题
 * 
 * */

public class MedicalParameter {
	
	/*
	 * 解决注入攻击问题
	 * 
	 * 通过正则进行数据的替换
	 * 
	 * */
	 public static String TransactSQLInjection(String str)
     {
           return str.replaceAll(".*([';]+|(--)+).*", " ");
     }
	 
	 /*
	  * 检测URL参数是否合法
	  * 
	  * retrun true参数合法
	  * retrun false参数不合法
	  * */
	 public static Boolean DetectionParameter(Object parameter){
		 
		 //判断参数是否为null或者为空
		 if(parameter == ""||parameter == null){
			 return false;
		 }
		 
		 //防止注入攻击
		 MedicalParameter.TransactSQLInjection(parameter.toString());
		 //检测关键字符
		 Pattern pat = Pattern.compile("select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute");
		 Matcher mat = pat.matcher(parameter.toString());
		 
		 if(mat.find()){
			 return false;
		 }
		 
		 return true;
	 }
	
	
}
