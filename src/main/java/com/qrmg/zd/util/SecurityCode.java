package com.qrmg.zd.util;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * 工具类：生成验证码字符串
 *
 * @author liuqinxiao
 * @date 2016年1月23日 下午2:33:03
 * @version 1.0
 */

public class SecurityCode {
	/**
	 * 验证码难度级别 
	 * Simple-数字 
	 * Medium-数字和小写字母 
	 * Hard-数字和大小写字母
	 */
	public enum SecurityCodeLevel {
		Simple, 
		Medium, 
		Hard
	}

	/**
	 * 产生默认验证码，4位中等难度
	 * 
	 * @return
	 */
	public static String getSecurityCode() {
		return getSecurityCode(4, SecurityCodeLevel.Medium, false);
	}

	/**
	 * 产生长度和难度任意的验证码
	 * 
	 * @param length 验证码字符串长度
	 * @param level 难度级别
	 * @param isCanRepeat 是否允许验证码中存在重复字符
	 * @return
	 */
	public static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		// 字符集合（--除去易混淆的数字0,1,字母l,o,O）
		char[] codes = { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' 
		};
		// 根据不同难度截取字符串
		if (level == SecurityCodeLevel.Simple) {
			codes = Arrays.copyOfRange(codes, 0, 10);
		} else if (level == SecurityCodeLevel.Medium) {
			codes = Arrays.copyOfRange(codes, 0, 36);
		}
		// 字符集和长度
		int n = codes.length;
		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
//			throw new RuntimeException(MessageFormat.format("调用SecurityCode.getSecurityCode({0},{1},{2})出现异常，当isCanRepeat为{2}时，传入参数{0}不能大于{3}", len, level, isCanRepeat, n));
			throw new RuntimeException(MessageFormat.format("调用SecurityCode.getSecurityCode({0},{1},{2})出现异常，当isCanRepeat为{2}时，传入参数{0}不能大于{3}", len, level, isCanRepeat, n));
		}
		// 存放抽取出来的字符
		char[] result = new char[len];
		// 判断能否出现重复字符
		if (isCanRepeat) {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
				// 必须确保不会再次抽取到那个字符，这里用数组中最后一个字符改写code[r],并将n-1
				codes[r] = codes[n - 1];
				n--;
			}
		}
		return String.valueOf(result);
	}
}
