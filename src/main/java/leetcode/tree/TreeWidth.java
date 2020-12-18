package leetcode.tree;

import leetcode.tree.base.TreeNode;
import leetcode.tree.base.TreeUtils;
import utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class TreeWidth {
    public static void main(String[] args) {
        test();
//        test1(args);
    }

    private static void test() {
        //        String line = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
        String line = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]";
        TreeNode root = TreeUtils.stringToTreeNode(line);
        TreeUtils.prettyPrintTree(root);

        LogUtils.d("----------------------");
        LogUtils.d("⚠️ treeNodeToString = [" + TreeUtils.treeNodeToString(root) + "]");
        LogUtils.d("----------------------");
        LogUtils.d("⚠️ Node.findWidth(root) = [" + findWidth(root) + "]");
    }

    private static void test1(String[] args) {
        try {
            fromLine(args);//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromLine(String[] args) throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while ((line = in.readLine()) != null) {
            TreeNode root = TreeUtils.stringToTreeNode(line);
            TreeUtils.prettyPrintTree(root);
        }
    }

    public static <T> int findWidth(TreeNode<T> root) {
        if (root == null)
            return 0;
        int maxwidth = 1, length = 0; //maxwidth 表示最大宽值，length表示当前层的宽度
        TreeNode<T> tmp;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (length <= 0)
                length = queue.size();
            if (length > maxwidth)
                maxwidth = length;
            while (length-- > 0) {
                tmp = queue.poll();
                if (tmp.left != null)
                    queue.add(tmp.left);
                if (tmp.right != null)
                    queue.add(tmp.right);
            }
        }
        return maxwidth;
    }
}
