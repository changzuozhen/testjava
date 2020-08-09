package tests;

import utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SchoolTimeTable {
    static DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    static long oneClass = 45 * 60 * 1000;
    static long rest = 10 * 60 * 1000;

    public static void main(String[] args) {
        Date date;

        date = new Date(2019, 1, 1, 8, 30, 0);
        LogUtils.d("---上午---");
        logoutTable(date, 4);
        LogUtils.d("---下午---");
        date = new Date(2019, 1, 1, 13, 20, 0);
        logoutTable(date, 2);

    }

    private static void logoutTable(Date date, int classCount) {
        for (int i = 0; i < classCount; i++) {
            LogUtils.v("上课: " + dateFormat.format(date) + " - " + dateFormat.format(date.getTime() + oneClass));
            date = new Date(date.getTime() + oneClass);
            if (i == classCount - 1) {
                return;
            }
            LogUtils.i("休息: " + dateFormat.format(date) + " - " + dateFormat.format(date.getTime() + rest));
            date = new Date(date.getTime() + rest);
        }
    }
}
