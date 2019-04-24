package utils;

import java.util.List;

public class StringBuilderUtils {
    public static StringBuilder fromInt1(int[] args) {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) result.append(',');
                result.append(args[i]);
            }
        }
        result.append(']');
        return result;
    }

    public static StringBuilder fromInt2(int[][] args) {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) result.append(',');
                result.append('[');
                for (int i1 = 0; i1 < args[i].length; i1++) {
                    if (i1 > 0) result.append(',');
                    result.append(args[i][i1]);
                }
                result.append(']');
            }
        }
        result.append(']');
        return result;
    }

    public static StringBuilder fromInt2(List<int[]> args) {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (args != null) {
            for (int i = 0; i < args.size(); i++) {
                if (i > 0) result.append(',');
                result.append('[');
                for (int i1 = 0; i1 < args.get(i).length; i1++) {
                    if (i1 > 0) result.append(',');
                    result.append(args.get(i)[i1]);
                }
                result.append(']');
            }
        }
        result.append(']');
        return result;
    }
}
