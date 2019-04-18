package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    /**
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void d(CharSequence msg, int stackIndex) {
        StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
        String log = build(msg, ste);
        System.out.println(log);
    }

    public static void d(Object msg) {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String log = build(msg, ste);
        System.out.println(log);
    }

    /**
     * 制作打log位置的文件名与文件行号详细信息
     */
    private static String build(Object log, StackTraceElement ste) {
        StringBuilder buf = new StringBuilder();


        buf.append("[").append(dateFormat.format(new Date())).append("] ");
//        buf.append("[").append(Thread.currentThread().getId()).append("]");
        String threadName = Thread.currentThread().getName();
        buf.append("[").append(threadName).append("]");

        for (int i = 0; i < 15 - threadName.length(); i++) {
            buf.append(' ');
        }

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
        buf.append(log);
        return buf.toString();
    }
}
