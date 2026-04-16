import java.util.Stack;

public class ExpressionConversion {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            // If '('
            else if (c == '(') {
                stack.push(c);
            }
            // If ')'
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            // Operator
            else {
                while (!stack.isEmpty() && 
                       precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        // Reverse
        String reversed = reverse(exp);

        // Replace brackets
        char[] arr = reversed.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') arr[i] = ')';
            else if (arr[i] == ')') arr[i] = '(';
        }

        // Convert to postfix
        String postfix = infixToPostfix(String.valueOf(arr));

        // Reverse again
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String exp = "(A+B)*C";

        System.out.println("Infix: " + exp);
        System.out.println("Postfix: " + infixToPostfix(exp));
        System.out.println("Prefix: " + infixToPrefix(exp));
    }
}