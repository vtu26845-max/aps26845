class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int left = 0;
        int right = n - 1;
        int p = n - 1; 
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[p--] = nums[left] * nums[left];
                left++;
            } else {
                ans[p--] = nums[right] * nums[right];
                right--;
            }
        }
        return ans;
    }
}