package com.partkyle.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.INITIALIZE;

import com.partkyle.prefix.parser.BasicScanner;
import com.partkyle.prefix.parser.InfixParser;
import com.partkyle.prefix.parser.Scanner;

public class InfixToPrefixConverter {
	public static void main(String[] args) {
		String expression = null;
		while (!"exit".equals(expression)) {
			expression = getRawInput("\n> ");
			Scanner scanner = new BasicScanner(expression);
			InfixParser parser = new InfixParser();

			System.out.println(parser.parse(scanner));
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
