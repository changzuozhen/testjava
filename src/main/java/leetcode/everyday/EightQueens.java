package leetcode.everyday;

public class EightQueens{
    public static void main(String[] ards){
        T t = new T();
        int arr[] = new int[8];//使用一维数组保存 棋盘规模 8*8
        t.putQueen(arr,0);
        System.out.println("八皇后问题一共有" + t.count +"情况");

    }
}


class T{
    //验证功能:判断棋子放在第几行 是否符合八皇后的规则： 与其他棋子 不能同列 不能同行 不能同一斜线
    //如果符合八皇后游戏规则 返回 true 否则 返回false 使用boolean返回类型
    // arr 表示棋盘 n 表示放入棋子在第n+1行 arr[n] 表示放入棋子在第 arr[n] + 1列
    public boolean verify(int arr[],int n){
        for (int i = 0;i < n ;i++ ) {
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }

        return true;
    }

    //八皇后棋子放置策略：
    //用一维数组arr 表示棋盘 i 表示初始棋子放置棋盘第i+1行
    //arr[i] 表示棋盘第arr[i]+1列
    //设置递归的出口 当棋子放到第8行 验证成功时 即 verify(arr,7) 返回true
    //打印八皇后放置棋子的8个位置

    int count = 0;//全局变量
    public void putQueen(int[] arr,int i){
        if (verify(arr,7)) {

            count++;
            System.out.println("\n第" + count + "种：");
            System.out.println("arr 数组：");
            //打印 符合条件的 arr[] 数组
            array(arr);

        }else{

            for (int j = 0;j < 8 ;j++ ) {
                //j 表示列  循环遍历 第0-7列
                arr[i] = j;
                //验证是否符合八皇后规则
                if (verify(arr,i)) {
                    //如果 验证返回true 则 递归调用 putQueen()方法
                    //继续 放置下一行的棋子位置(行和列：i 和 j) 并验证 位置是否符合八皇后规则
                    putQueen(arr,i + 1);
                }
            }
            //如果 行为第i+1行 棋子 循环遍历 放置0——7列
            //都不符合八皇后规则 即 verify(arr,i) 返回false
            //则 不会执行 if里面的语句 putQueen(arr,i+1);
            //即 不会继续下一行 放置棋子 而是回溯到 for语句
            //继续遍历 数组列表 即 这一行的第2列
        }
    }

    //一维数组变二维数组 并打印相对应的图形
    public void array(int[] arr){

        char[][] arr1 = new char[arr.length][arr.length];
        for (int i = 0;i < arr1.length ;i++ ) {
            for (int j = 0;j <arr1[i].length;j++ ) {
                arr1[i][j] = '*';
                arr1[i][arr[i]] = 'Q';

                System.out.print(arr1[i][j] + " ");
            }
            System.out.println();
        }

    }



}