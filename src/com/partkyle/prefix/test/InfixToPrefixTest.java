package com.partkyle.prefix.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.partkyle.prefix.expression.Expression;
import com.partkyle.prefix.parser.BasicScanner;
import com.partkyle.prefix.parser.InfixParser;

public class InfixToPrefixTest {

	@Test
	public void testSimple() {
		InfixParser parser = new InfixParser(new BasicScanner("3"));
		assertEquals("3", parser.parse().toPrefix());
	}

	@Test
	public void testAddition() {
		InfixParser parser = new InfixParser(new BasicScanner("1 + 1"));
		assertEquals("(+ 1 1)", parser.parse().toPrefix());
	}

	@Test
	public void testMultiplication() {
		InfixParser parser = new InfixParser(new BasicScanner("2 * 5 + 1"));
		Expression result = parser.parse();
		if ("(+ 1 (* 2 5))".equals(result.toPrefix())) {

		} else if ("(+ (* 2 5) 1)".equals(result.toPrefix())) {

		} else {
			fail(String.format("%s was not the correct answer", result));
		}
	}

	@Test
	public void testParenthesis() {
		InfixParser parser = new InfixParser(new BasicScanner("2 * ( 5 + 1 )"));
		Expression result = parser.parse();
		if ("(* (+ 5 1) 2)".equals(result.toPrefix())) {

		} else if ("(* 2 (+ 5 1))".equals(result.toPrefix())) {

		} else {
			fail(String.format("%s was not the correct answer", result));
		}
	}

	@Test
	public void testComplex() {
		InfixParser parser = new InfixParser(new BasicScanner("3 * x + ( 9 + y ) / 4"));
		assertEquals("(+ (* 3 x) (/ (+ 9 y) 4))", parser.parse().toPrefix());
	}
}
