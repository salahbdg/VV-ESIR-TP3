package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        // Create a stack to store the opening parentheses
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            // If the character is an opening bracket, push it onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If the character is a closing bracket, we check for its matching opening bracket
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false; // If there is a closing bracket without a matching opening bracket we return false
                }
                char openBracket = stack.pop();
                if (!isMatchingPair(openBracket, ch)) {
                    return false; // when the brackets doesn't match
                }
            }
        }
        // If the stack is empty, it means all brackets were matched; otherwise, they were not
        return stack.isEmpty();
    }

    // A method helps to check if the brackets are matching pairs
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || 
               (open == '{' && close == '}') || 
               (open == '[' && close == ']');
    }
}
