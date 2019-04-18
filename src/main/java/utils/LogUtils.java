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
            90,
            1,
            30,
            4,
            7,
            34,
            94,
            36,
            96,
            32,
            33,
            93,
            35,
            95,
            91,
            31,
    };


    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String color(int index) {
        return "\u001B[" + colorIndex[index % colorIndex.length] + "m";
//        switch (index % 7) {
//            case 0:
//                return ANSI_BLUE;
//            case 1:
//                return ANSI_CYAN;
//            case 2:
//                return ANSI_GREEN;
//            case 3:
//                return ANSI_WHITE;
//            case 4:
//                return ANSI_YELLOW;
//            case 5:
//                return ANSI_PURPLE;
//            case 6:
//                return ANSI_RED;
//        }
//        return ANSI_WHITE;
    }


    public static void log(CharSequence msg, int colorindex) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(color(colorindex) + log + ANSI_RESET);
//                + "\t\t" + colorIndex[colorindex%colorIndex.length]);
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
