package tests;

import utils.LogUtils;

class Test {
    public static void main(String[] args) {
//        testInter();
        try {
            System.out.println(flipStr("I am a student.", " ", "."));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testInter() {
        LogUtils.d(Integer.MIN_VALUE);
        LogUtils.d(Integer.MAX_VALUE);

        int limit = -Integer.MAX_VALUE;
        LogUtils.d(limit);
        limit = Integer.MIN_VALUE;
        LogUtils.d(limit);

        LogUtils.d(Integer.parseInt("-2147483648"));

        Integer i1 = 200;
        Integer i2 = 201;
        LogUtils.d(200 == (i2 - 1));
    }

    public static String flipStr(String sentence, String spliter, String endMark) throws Exception {
        if (null == spliter || spliter.length() == 0) {
            throw new Exception("分隔符 参数错误");
        }
        if (null == endMark || endMark.length() == 0) {
            throw new Exception("结尾符 参数错误");
        }
        if (null == sentence || sentence.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder("");
        String[] splits = sentence.split(spliter);
        boolean hasEndMark = false;
        if (splits[splits.length - 1].endsWith(endMark)) {
            splits[splits.length - 1] = splits[splits.length - 1].replace(endMark, "");
            hasEndMark = true;
        }
        for (int i = splits.length - 1; i >= 0; i--) {
            sb.append(splits[i]);
            if (i > 0) {
                sb.append(spliter);
            }
        }
        if (hasEndMark) {
            sb.append(endMark);
        }
        return sb.toString();
    }

}


