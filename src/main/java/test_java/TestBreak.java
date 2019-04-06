package test_java;

public class TestBreak {
    public static void main(String[] args) {
        out:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (j >= 2) break out;
                System.out.println(j);
            }
            System.out.println("break");
        }
    }
}


