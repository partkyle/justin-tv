package com.partkyle.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.partkyle.prefix.parser.BasicScanner;
import com.partkyle.prefix.parser.InfixParser;

public class InfixToPrefixConverter {
	public static void main(String[] args) {
		String result = getRawInput("> ");
		while (!"exit".equals(result)) {
			InfixParser parser = new InfixParser(new BasicScanner(result));
			System.out.println(parser.parse());
			result = getRawInput("> ");
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

		return "";
	}

}
