package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    public static final boolean SHOWCOLORINFO = true;

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


    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public static String color(int index) {
//        index = (index % colorIndex.length + colorIndex.length) % colorIndex.length;
        return "\u001B["
                + colorIndex[index]
//                + index
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
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void d(CharSequence msg, int stackIndex) {
        StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
        String log = build(msg, ste);
        System.out.println(ANSI_WHITE
                + (SHOWCOLORINFO ? String.format("ANSI_WHITE: %4s ", ANSI_WHITE.substring(2)) : "")
                + log + ANSI_RESET);
    }

    public static void v(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_BLUE
                + (SHOWCOLORINFO ? String.format("ANSI_BLUE:  %4s ", ANSI_BLUE.substring(2)) : "")
                + log + ANSI_RESET);
    }

    public static void d(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_WHITE
                + (SHOWCOLORINFO ? String.format("ANSI_WHITE: %4s ", ANSI_WHITE.substring(2)) : "")
                + log + ANSI_RESET);
    }

    public static void i(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_YELLOW
                + (SHOWCOLORINFO ? String.format("ANSI_YELLOW:%4s ", ANSI_YELLOW.substring(2)) : "")
                + log + ANSI_RESET);
    }

    public static void w(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_PURPLE
                + (SHOWCOLORINFO ? String.format("ANSI_PURPLE:%4s ", ANSI_PURPLE.substring(2)) : "")
                + log + ANSI_RESET);
    }

    public static void e(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(ANSI_RED
                + (SHOWCOLORINFO ? String.format("ANSI_RED:   %4s ", ANSI_RED.substring(2)) : "")
                + log + ANSI_RESET);
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
        StringBuilderUtils.multiply(buf," ",45 - buf.length());
//        buf.append("[").append(Thread.currentThread().getId()).append("]");
        String threadName = Thread.currentThread().getName();
        buf.append("[").append(threadName).append("]: ");
        StringBuilderUtils.multiply(buf," ",17 - threadName.length());
        buf.append(log);
        return buf.toString();
    }

}
