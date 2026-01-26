class Solution {
    //https://leetcode.com/problems/rotate-array/
    public void rotate(int[] nums, int k) {
        // if some one says that rotate a array by 6 and the lenght of array is 5, 
        // then the array will be rotate by just 1 (6 % 5)
        k = k%nums.length;
        // we take extra space for the last k element
        int[] last_k_elements = new int[k];
        //we store the the last k element in the array 
        for(int i = nums.length - k, j= 0; i < nums.length && j<k; i++, j++)
        {
            last_k_elements[j] = nums[i];
        }
        // shift all elements up to lenght- k to their new position which is i+k
        for(int i = nums.length - k - 1; i >= 0 ; i-- )
        {
            nums[i+k] = nums[i];
        }
        // then assign the last k elements to the first k elements 
        for(int i  = 0; i < k; i++)
        {
            nums[i] = last_k_elements[i];
        }
    }
}