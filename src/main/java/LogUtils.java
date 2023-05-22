import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    public static final boolean SHOWCOLORINFO = true;

    public static final int[] colorIndex = {
            // 黑
            30, // 0
            4, // 1
            100, // 2
            7, // 3
            40, // 4

            // 蓝
            34, // 5
            44, // 6

            //绿
            36, // 7
            46, // 8

            // 靛
            96, // 9

            // 黄
            93, //10
            103, //11

            // 红
            95, //12
            31, //13
            41, //14
    };
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = color(5);//"\u001B[34m";
    public static final String ANSI_WHITE = color(3);//"\u001B[30m";
    public static final String ANSI_PURPLE = color(12);//"\u001B[95m";
    public static final String ANSI_YELLOW = color(10);//"\u001B[93m";
    public static final String ANSI_RED = color(13);//"\u001B[31m";


    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String color(int index) {
//        index = (index % colorIndex.length + colorIndex.length) % colorIndex.length;
        return "\u001B["
                + colorIndex[index]
                + "m";
    }


    public static void log(Object msg, int colorindex) {
        log(msg, colorindex, 2);
    }

    /**
     * @param msg
     * @param colorindex
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void log(Object msg, int colorindex, int stackIndex) {
        colorindex = (colorindex % colorIndex.length + colorIndex.length) % colorIndex.length;
        StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
        String log = build(msg, ste);
        System.out.println(
                color(colorindex)
                        + (SHOWCOLORINFO ? String.format("color[%2d]:  %3d  ", colorindex, colorIndex[colorindex]) : "")
                        + log
                        + ANSI_RESET
        );
    }

    /**
     * @param colorStr
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void logWithColorStr(Object msg, String colorStr, int stackIndex) {
        StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
        String log = build(msg, ste);
        System.out.println(colorStr
                + (SHOWCOLORINFO ? String.format("LOG      : %4s  ", colorStr.substring(2, colorStr.length() - 1)) : "")
                + log + ANSI_RESET);
    }

    public static void v(Object msg) {
        logWithColorStr(msg, ANSI_BLUE, 2);
    }

    public static void v(Object msg, int stackIndex) {
        logWithColorStr(msg, ANSI_BLUE, stackIndex + 1);
    }

    public static void d(Object msg) {
        logWithColorStr(msg, ANSI_WHITE, 2);
    }

    /**
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void d(Object msg, int stackIndex) {
        logWithColorStr(msg, ANSI_WHITE, stackIndex + 1);
    }

    public static void i(Object msg) {
        logWithColorStr(msg, ANSI_YELLOW, 2);
    }

    public static void i(Object msg, int stackIndex) {
        logWithColorStr(msg, ANSI_YELLOW, stackIndex + 1);
    }

    public static void w(Object msg) {
        logWithColorStr(msg, ANSI_PURPLE, 2);
    }

    public static void w(Object msg, int stackIndex) {
        logWithColorStr(msg, ANSI_PURPLE, stackIndex + 1);
    }

    public static void e(Object msg) {
        logWithColorStr(msg, ANSI_RED, 2);
    }

    public static void e(Object msg, int stackIndex) {
        logWithColorStr(msg, ANSI_RED, stackIndex + 1);
    }

    /**
     * 制作打log位置的文件名与文件行号详细信息
     */
    private static String build(Object log, StackTraceElement ste) {
        StringBuilder buf = new StringBuilder();
        buf.append("[").append(dateFormat.format(new Date())).append("] ");
//        buf.append("[ ] ");
        if (ste.isNativeMethod()) {
            buf.append("(Native Method)");
        } else {
            String fName = ste.getFileName();

            if (fName == null) {
                buf.append("(Unknown Source)");
            } else {
                int lineNum = ste.getLineNumber();
                buf.append('(');
                buf.append(fName);
                if (lineNum >= 0) {
                    buf.append(':');
                    buf.append(lineNum);
                }
                buf.append("):");
            }
        }
        multiply(buf, " ", 45 - buf.length());
//        buf.append("[").append(Thread.currentThread().getId()).append("]");
        String threadName = Thread.currentThread().getName();
        buf.append("[").append(threadName).append("]: ");
        multiply(buf, " ", 17 - threadName.length());
        buf.append(log);
        return buf.toString();
    }

    public static StringBuilder multiply(StringBuilder stringBuilder, CharSequence charSequence, int times) {
        for (int i = 0; i < times; i++) {
            stringBuilder.append(charSequence);
        }
        return stringBuilder;
    }
}
