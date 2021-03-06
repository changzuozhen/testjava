package leetcode.commonutils;

public class Utils {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        if (input.length() < 3) return new int[0];
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

}
