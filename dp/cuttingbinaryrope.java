// https://practice.geeksforgeeks.org/problems/cutting-binary-string1342/1

import java.util.Arrays;

class cuttingbinaryrope {
    public static void main(String[] args) {
        System.out.println(cuts("1011011011011"));
    }

    public static int cuts(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return util(s, 0, n - 1, dp);
    }
    // 1 0 1 1 0 1 1 0 1

    public static int util(String s, int start, int end, int[][] dp) {

        System.out.println("function call : with string : " + s.substring(start, end + 1));

        if (s.charAt(start) == '0')
            return -1;
        // no element left
        if (start > end) {
            System.out.println("start>end");
            return 0;
        }

        if (dp[start][end] != -1) {
            // System.out.println("dp value return" + start+ " "+end+" "+dp[start][end]);
            return dp[start][end];
        }

        if (help(s, start, end))
            return 1;
        int ans = Integer.MAX_VALUE;
        System.out.println("ans : max");

        for (int i = start; i <= end; i++) {
            // if a valid cut
            if (help(s, start, i)) {
                // System.out.println("calling second half of string");
                int second = util(s, i + 1, end, dp);

                if (second != -1) {
                    ans = Math.min(ans, second + 1);

                } else {
                    System.out.println("return back not valid");
                }
            }
        }
        System.out.println("returnring ans : " + ans);
        dp[start][end] = ans == Integer.MAX_VALUE ? -1 : ans;
        // System.out.println("dp value" + start+ " "+end+" "+dp[start][end]);
        return dp[start][end];
    }

    // function to check if the binary string is a power of 5 or not

    public static boolean help(String str, int s, int e) {
        int p = 0;
        int val = 0;
        for (int i = e; i >= s; i--) {
            if (str.charAt(i) == '1') {
                val += Math.pow(2, p);
            }
            p++;
        }

        while (val > 1) {
            if (val % 5 != 0)
                return false;
            val = val / 5;
        }
        return true;
    }
}
