package leetcode.Palindrome;

import utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割字符串使得每个部分都是回文数
 * 131. Palindrome Partitioning (Medium)
 * https://leetcode.com/problems/palindrome-partitioning/description/
 */
public class Palindrome {
    private List<List<String>> ret;

    public List<List<String>> partition(String s) {
        ret = new ArrayList<>();
        doPartition(new ArrayList<>(), s);
        return ret;
    }

    private void doPartition(List<String> list, String s) {
        if (s.length() == 0) {
            ret.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, 0, i)) {
                list.add(s.substring(0, i + 1));
                doPartition(list, s.substring(i + 1));
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int begin, int end) {
        while (begin < end)
            if (s.charAt(begin++) != s.charAt(end--))
                return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "aabcac";
        //[[a, a, b, c, a, c], [a, a, b, cac], [aa, b, c, a, c], [aa, b, cac]]
        LogUtils.d(new Palindrome().partition(s));
    }
}
