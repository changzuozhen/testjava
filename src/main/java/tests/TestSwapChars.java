package tests;

class TestSwapChars {
    public static class Main {
        public static void main(String[] args) {
            System.out.println("www.baidu.com");
            System.out.println(handleChars("www.baidu.com".toCharArray()));
        }

        public static char[] handleChars(char[] chars) {
            int start = 0;
            int end = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '.' || i == chars.length - 1) {
                    if (i == chars.length - 1) {
                        end = i;
                    } else {
                        end = i - 1;
                    }
                    swap(chars, start, end);
                    start = i + 1;
                    continue;
                }
            }
            return chars;
        }

        public static char[] swap(char[] chars, int start, int end) {
            char tmp = 0;
            for (int i = 0; i < (end - start) / 2; i++) {
                if (end - i == start + i) return chars;
                tmp = chars[start + i];
                chars[start + i] = chars[end - i];
                chars[end - i] = tmp;
            }
            return chars;
        }
    }
}
