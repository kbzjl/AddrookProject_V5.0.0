package cn.test.addrook.commons.util;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/12 1:07
 * @Version 1.0
 */
public class ValidationUtil {
	//手机号码正则表达式
	private static Pattern CELLPHONE_REGEX = Pattern.compile("1[0-9]{10}");
	//登录密码正则表达式
	private static Pattern PASSWORD_REGEX = Pattern.compile("[A-Za-z0-9]{6}");
	//邮箱地址正则表达式
	private static Pattern EMAIL_REGEX = Pattern.compile("\\W+@\\w+.com");

	//提供方法 判断手机号码是否符合
	public static boolean isCellphone(String cellphone){
		if (StrUtil.isNotBlank(cellphone) && CELLPHONE_REGEX.matcher(cellphone).matches()){
			return true;
		}
		return false;
	}
	//提供方法 判断登录密码是否符合
	public static boolean isPassword(String password){
		if(StrUtil.isNotBlank(password) && PASSWORD_REGEX.matcher(password).matches()){
			return true;
		}
		return false;
	}
	//提供方法 判断电子邮件是否符合
	public static boolean isEmail(String email){
		if(StrUtil.isNotBlank(email) && EMAIL_REGEX.matcher(email).matches()){
			return true;
		}
		return false;
	}
}
