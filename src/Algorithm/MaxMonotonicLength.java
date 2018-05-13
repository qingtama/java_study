package Algorithm;

/**
 * 求最长单调递增子序列的长度
 */
public class MaxMonotonicLength {
    static int maxSubLen(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        int maxLen = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++){
            if(nums[i] > nums[i-1])
                dp[i] = dp[i-1] + 1;
            else{
                maxLen = Math.max(maxLen, dp[i-1]);
                dp[i] = 1;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,5,8,3,8,5,6,9,10,12,12,9,10};
        System.out.println(maxSubLen(a));
    }
}
