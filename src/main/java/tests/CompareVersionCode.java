package tests;

class CompareVersionCode {
    public static class Main {
        public static void main(String[] args) {
            test();
        }

        private static void test() {
            try {
                /**
                 * 8 .9.10  8.11. 3:-1
                 * 8 .9.10  8.9.10:0
                 * 8 .9.10  8.9.9:1
                 */
                System.out.println("8 .9.10" + "  " + "8.11. 3:" + compareVersionString("8 .9.10", "8.11. 3"));
                System.out.println("8 .9.10" + "  " + "8.9.10:" + compareVersionString("8 .9.10", "8.9.10"));
                System.out.println("8 .9.10" + "  " + "8.9.9:" + compareVersionString("8 .9.10", "8.9.9"));
            } catch (Exception invalidArgument) {
                invalidArgument.printStackTrace();
            }
        }

        /**
         * @return == 0: version相同
         * @throws Exception
         */
        public static int compareVersionString(String v1, String v2) throws Exception {
            if (v1 != null) {
                v1 = v1.replaceAll(" ", "");
            }
            if (v2 != null) {
                v2 = v2.replaceAll(" ", "");
            }
            if (!v1.matches("\\d+(\\.\\d+)+")) {
                throw new Exception("version format error! v1 : " + v1);
            }
            if (!v2.matches("\\d+(\\.\\d+)+")) {
                throw new Exception("version format error! v2:" + v2);
            }
            if (isEmpty(v1)) {
                if (isEmpty(v2)) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (isEmpty(v2)) {
                return 1;
            }
            if (v1.equals(v2)) {
                return 0;
            }

            String[] ver1Array = v1.split("\\.");
            String[] ver2Array = v2.split("\\.");
            for (int i = 0; i < Math.min(ver1Array.length, ver2Array.length); i++) {
                int n1 = Integer.parseInt(ver1Array[i]);
                int n2 = Integer.parseInt(ver2Array[i]);
                if (n1 != n2) {
                    return n1 > n2 ? 1 : -1;
                }
            }
            if (ver1Array.length > ver2Array.length) {
                return 1;
            } else if (ver1Array.length < ver2Array.length) {
                return -1;
            } else {
                return 0;
            }
        }

        public static boolean isEmpty(final CharSequence s) {
            if (s == null) {
                return true;
            }
            return s.length() == 0;
        }
    }
}
