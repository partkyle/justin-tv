package com.partkyle.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InfixToPrefixConverter {
	public static void main(String[] args) {
		String result = null;
		while (!"exit".equals(result)) {
			result = getRawInput(">");
			List<Number> operands = new ArrayList<Number>();
			List<String> operation = new ArrayList<String>();
			for (String s : result.split("[^\\d()+-/*^]")) {
				System.out.println(s);
			}
		}
	}

	public static String getRawInput(String prompt) {
		BufferedReader in;
		try {
			System.out.print(prompt);
			in = new BufferedReader(new InputStreamReader(System.in));
			String result = in.readLine();
			return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return "exit";
	}
}
