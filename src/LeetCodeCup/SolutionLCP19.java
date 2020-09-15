package LeetCodeCup;

public class SolutionLCP19 {
    public static void main(String[] args) {
        String leaves = "yryyyrryryryryryryryyyrrrrrrrrrrrryyryryyryryyyyyyyyyyyyyyrrrrrrrrrrrrryyryryryryrrryyyyyyy";
        SolutionLCP19 solution = new SolutionLCP19();
        int ans = solution.minimumOperations(leaves);
        System.out.println(ans);
    }

    public int minimumOperations(String leaves) {
        int length = leaves.length();
        int[] help = new int[length];
        for (int i = 1; i < length; ++i) {
            if (leaves.charAt(i) == 'y') help[i] = help[i - 1] + 1;
            else help[i] = help[i - 1];
        }
        int best = 0;
        int sum = 0;
        int modify = 0;
        int ans = Integer.MAX_VALUE;
        if (leaves.charAt(0) == 'y') ++modify;
        for (int i = 1; i < length - 1; ++i) {
            int base = i - help[i] + help[length - 1] - help[i];
            ans = Math.min(ans, base - best);
            if (leaves.charAt(i) == 'y') --sum;
            else ++sum;
            best = Math.max(best, sum);
        }
        return ans + modify;
    }
}
