
package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testBalancedInputs() {
        // Balanced cases
        assertTrue(isBalanced(""));                  // if the string is empty
        assertTrue(isBalanced("()"));                // Single pair of parentheses
        assertTrue(isBalanced("{}"));                // Single pair of curly braces
        assertTrue(isBalanced("[]"));                // Single pair of square brackets
        assertTrue(isBalanced("{[()]}"));            // Mixed brackets
        assertTrue(isBalanced("[(())]"));            // More complex nested structure
        assertTrue(isBalanced("{[()()()()]}"));      // Multiple nested pairs
    }

    @Test
    void testUnbalancedInputs() {
        // Unbalanced cases
        assertFalse(isBalanced("("));                 // Single unmatched opening bracket
        assertFalse(isBalanced(")"));                 // Single unmatched closing bracket
        assertFalse(isBalanced("{(])"));              // Incorrectly nested brackets
        assertFalse(isBalanced("{[}]"));              // Mismatched pairs
        assertFalse(isBalanced("{{[()]}}]"));         // Extra closing bracket
        assertFalse(isBalanced("}{"));                // Unmatched pairs
        assertFalse(isBalanced("((())))))"));         // Extra closing bracket
    }

    @Test
    void testEdgeCases() {
        // Edge cases
        assertTrue(isBalanced("abc"));                // No brackets
        assertTrue(isBalanced("((((()))))"));         // Long balanced brackets
        assertFalse(isBalanced("((((())))))))"));     // Long unbalanced brackets
        assertFalse(isBalanced("{[()]}}"));            // Unmatched pairs
    }
}
