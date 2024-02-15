package org.ryuuzkaiumi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Util {
	public static int str2Int(String inputstr) {
		
		int result = -1;
		String res;
		res = extractNumber(inputstr);
		System.out.println("한번 함수 돌면 : " + res);
		if (!(res == null || res.equals(""))) {
			
			result = Integer.parseInt(res);
		} 
		
		return result;
	}
	
	private static String extractNumber(String str) {
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(matcher.group());
			
		}
		
		return result.toString();
	}
	

}
