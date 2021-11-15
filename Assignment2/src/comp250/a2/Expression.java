package comp250.a2;

/*
Student Name: Zhu Yi
Student ID: 260716006
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression {
	private ArrayList<String> tokenList;

	// Constructor
	/**
	 * The constructor takes in an expression as a string and tokenizes it
	 * (breaks it up into meaningful units) These tokens are then stored in an
	 * array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException {
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		// ADD YOUR CODE BELOW HERE
		//Check whether the token is an integer
		for (int i = 0; i < expressionString.length(); i++) {
			if (expressionString.charAt(i) >= '0' && expressionString.charAt(i) <= '9') {
				int j = i + 1;
				while (expressionString.charAt(j) >= '0' && expressionString.charAt(j) <= '9') {
					j++;
				}
				token = token.append(expressionString.substring(i, j));
				tokenList.add(token.toString());
				token.delete(0, token.length());
				i = j - 1;
				//Check whether the token is an operator
			} else if ((expressionString.charAt(i) == '+' && expressionString.charAt(i + 1) == '+')
					|| (expressionString.charAt(i) == '-' && expressionString.charAt(i + 1) == '-')) {
				int j = i + 2;
				token = token.append(expressionString.substring(i, j));
				tokenList.add(token.toString());
				token.delete(0, token.length());
				i = j - 1;
				//Check whether there is a space
			} else if (!(expressionString.substring(i, i + 1).equals(" "))) {
				tokenList.add(expressionString.substring(i, i + 1));
			}
		}
		// ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the
	 * expression Evaluation is done using 2 stack ADTs, operatorStack to store
	 * operators and valueStack to store values and intermediate results. - You
	 * must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval() {
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		// ADD YOUR CODE BELOW HERE
		//Do not store '(' or '['
		for (int i = 0; i < tokenList.size(); i++) {
			String token = tokenList.get(i);
			if (token.equals("(") || token.equals("[")) {
				continue;
				//Store integer
			} else if (isInteger(token)) {
				valueStack.push(Integer.parseInt(token));
			} else {
				//Store ')' and ']'
				operatorStack.push(token);
				if (token.equals(")") || token.equals("]")) {
					//Get first value
					int a1 = valueStack.pop();
					if (token.equals(")")) {
						operatorStack.pop();
						//Get operator
						String op = operatorStack.pop();
						//Deal with addition
						if (op.equals("+")) {
							int a2 = valueStack.pop();
							valueStack.push(a1 + a2);
						//Deal with subtraction
						} else if (op.equals("-")) {
							int a2 = valueStack.pop();
							valueStack.push(a2 - a1);
						//Deal with multiplication
						} else if (op.equals("*")) {
							int a2 = valueStack.pop();
							valueStack.push(a1 * a2);
						//Deal with division
						} else if (op.equals("/")) {
							int a2 = valueStack.pop();
							valueStack.push(a2 / a1);
						//Deal with ++
						} else if (op.equals("++")) {
							valueStack.push(++a1);
						//Deal with --
						} else if (op.equals("--")) {
							valueStack.push(--a1);
						}
						//Deal with abs value
					} else if (token.equals("]")) {
						operatorStack.pop();
						if (a1 >= 0) {
							valueStack.push(a1);
						} else if (a1 < 0) {
							valueStack.push(-a1);
						}
					}
				}
			}
		}
		return valueStack.peek();
		// ADD YOUR CODE ABOVE HERE

	}

	// Helper methods
	/**
	 * Helper method to test if a string is an integer Returns true for strings
	 * of integers like "456" and false for string of non-integers like "+" - DO
	 * NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element) {
		try {
			Integer.valueOf(element);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList. -
	 * DO NOT EDIT THIS METHOD
	 */

	@Override
	public String toString() {
		String s = new String();
		for (String t : tokenList)
			s = s + "~" + t;
		return s;
	}

}