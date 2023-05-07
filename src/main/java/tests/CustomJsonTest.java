package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CustomJsonTest {

    public static Map<String, Object> parseJson(String jsonString) {
        Map<String, Object> map = new HashMap<String, Object>();
        Stack<Map<String, Object>> stack = new Stack<Map<String, Object>>();
        stack.push(map);
        int i = 0;
        while (i < jsonString.length()) {
            char c = jsonString.charAt(i);
            if (c == '{' || c == '[') {
                Map<String, Object> newMap = new HashMap<String, Object>();
                stack.peek().put(String.valueOf(i), newMap);
                stack.push(newMap);
            } else if (c == '}' || c == ']') {
                stack.pop();
            } else if (c == ',') {
                int j = i + 1;
                while (j < jsonString.length() && Character.isWhitespace(jsonString.charAt(j))) {
                    j++;
                }
                if (j < jsonString.length() && (jsonString.charAt(j) == '{' || jsonString.charAt(j) == '[')) {
                    Map<String, Object> newMap = new HashMap<String, Object>();
                    stack.peek().put(String.valueOf(i), newMap);
                    stack.push(newMap);
                }
            } else if (c == ':') {
                int j = i + 1;
                while (j < jsonString.length() && Character.isWhitespace(jsonString.charAt(j))) {
                    j++;
                }
                if (j < jsonString.length() && jsonString.charAt(j) == '{') {
                    Map<String, Object> newMap = new HashMap<String, Object>();
                    stack.peek().put(String.valueOf(i), newMap);
                    stack.push(newMap);
                }
            } else if (Character.isDigit(c)) {
                int j = i + 1;
                while (j < jsonString.length() && Character.isDigit(jsonString.charAt(j))) {
                    j++;
                }
                String numberString = jsonString.substring(i, j);
                stack.peek().put(String.valueOf(i), Integer.parseInt(numberString));
                i = j - 1;
            } else if (c == '\"') {
                int j = i + 1;
                while (j < jsonString.length() && jsonString.charAt(j) != '\"') {
                    j++;
                }
                String stringLiteral = jsonString.substring(i + 1, j);
                stack.peek().put(String.valueOf(i), stringLiteral);
                i = j;
            }
            i++;
        }
        return map;
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\",\"age\":30,\"cars\":[{\"name\":\"Ford\",\"models\":[{\"name\":\"Fiesta\"},{\"name\":\"Focus\"},{\"name\":\"Mustang\"}]},{\"name\":\"BMW\",\"models\":[{\"name\":\"320\"},{\"name\":\"X3\"},{\"name\":\"X5\"}]}]}";
        Map<String, Object> map = parseJson(jsonString);
        System.out.println(map);
    }
}
