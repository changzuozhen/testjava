package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[90m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_PURPLE = "\u001B[95m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final int[] colorIndex = {
//            90,
//            1,
            30,
            4,
            100,
            7,
            40,
            34,
            44,
            36,
            46,
            96,
//            106,
            93,
            103,
//            35,
//            45,
            95,
//            105,
//            91,
//            101,
            31,
            41,
    };


    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String color(int index) {
//        index = (index % colorIndex.length + colorIndex.length) % colorIndex.length;
        return "\u001B["
                + colorIndex[index]
//                + index
                + "m";
    }


    public static void log(Object msg, int colorindex) {
        colorindex = (colorindex % colorIndex.length + colorIndex.length) % colorIndex.length;
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(color(colorindex)
                + String.format("color:%3d\t", colorIndex[colorindex])
                + log
                + ANSI_RESET
        );
    }

    /**
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void d(CharSequence msg, int stackIndex) {
        StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
        String log = build(msg, ste);
        System.out.println(ANSI_WHITE + log + ANSI_RESET);
    }

    public static void v(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_GREEN + log + ANSI_RESET);
    }

    public static void d(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_WHITE + log + ANSI_RESET);
    }

    public static void i(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_YELLOW + log + ANSI_RESET);
    }

    public static void w(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_PURPLE + log + ANSI_RESET);
    }

    public static void e(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_RED + log + ANSI_RESET);
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
                buf.append("):\t");
            }
        }

//        buf.append("[").append(Thread.currentThread().getId()).append("]");
        String threadName = Thread.currentThread().getName();
        buf.append("[").append(threadName).append("]: ");

        for (int i = 0; i < 17 - threadName.length(); i++) {
            buf.append(' ');
        }


        buf.append(log);
        return buf.toString();
    }
}
