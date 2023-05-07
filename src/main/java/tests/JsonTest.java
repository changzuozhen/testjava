package tests;

import com.google.gson.Gson;
import utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTest {

    private static DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

    private static class TestJson {
        boolean enable = true;
        long endTime;
        long startTime;


        public TestJson(long startTime, long min) {
            this.startTime = startTime;
            endTime = startTime + min * 60 * 1000;
        }

        @Override
        public String toString() {
            return "TestJson{" +
                    "startTime=" + dateFormat.format(new Date(startTime)) +
                    ", endTime=" + dateFormat.format(new Date(endTime)) +
                    '}';
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TestJson testJson = new TestJson(startTime, 5);
        LogUtils.d(new Gson().toJson(testJson));
        LogUtils.d(testJson);
    }
}
