package tests;

class TestInput {
    public static void main(String[] args) {
        int[] test = new int[]{2, 1, 4, 5, 7, 3, 9, 8, 6};
        int start = 0;
        int tmp = 0;

        for (int i = 0; i < test.length; i++) {
            tmp ^= i + 1;
            tmp ^= test[i];
            if (tmp == 0) {
                for (int j = start; j <= i; j++) {
                    System.out.println("" + (j + 1) + " " + test[i]);
                }
                start = i + 1;
            }
        }
    }
}
