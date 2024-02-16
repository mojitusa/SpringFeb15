package org.ryuuzakiumi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Util {
	public int str2Int(String inputstr) {
		
		int result = 0;
		String res;
		res = extractNumber(inputstr);
		
		if (!(res == null || res.equals(""))) {
			
			result = Integer.parseInt(res);
		} 
		
		return result;
	}
	
	private String extractNumber(String str) {
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(matcher.group());
			
		}
		
		return result.toString();
	}
	

}
