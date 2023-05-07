package tests;

public class TestAsm {
    public static int proc(int type, int value) {
        int start = 0;
        switch (type) {
            case 1:
                start = 3;
                break;
            case 2:
                start = 5;
                break;
            default:
                start = 7;
        }
        return start + value;
    }

    public static void main(String[] args) {
        TestAsm.proc(1, 1);
    }
}