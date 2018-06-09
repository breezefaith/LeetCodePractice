package t1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        return twoSum2(nums,target);
    }

    /**
     * 解法1：暴力法求解，通过两遍遍历求得结果
     * 分析：
     *      遍历每个元素 x，并查找是否存在一个值与 target - x 相等的目标元素。
     *
     * 时间复杂度:O(n^2)
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 解法2：两遍哈希表
     * 分析：
     *      通过以空间换取速度的方式，我们可以将查找时间从 O(n)降低到 O(1)。
     *  哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。我用
     *  “近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。但只要你
     *  仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为O(1)。一个简单
     *  的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加
     *  到表中。然后，在第二次迭代中，我们将检查每个元素所对应的目标元素
     *  （target - nums[i]）是否存在于表中。
     *  注意:该目标元素不能是nums[i]本身！
     *
     * 时间复杂度:O(n),我们把包含有 n个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1)，所以时间复杂度为 O(n)。
     * 空间复杂度：O(n),所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n 个元素
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result=new int[2];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            if (map.get(target-nums[i])!=null&&map.get(target-nums[i])!=i){
                result[0]=i;
                result[1]=map.get(target-nums[i]);
                break;
            }
        }
        return result;
    }

    /**
     * 解法3：向哈希表中存储的同时进行查找
     * 分析：
     *      在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是
     *  否已经存在当前元素所对应的目标元素。如果它存在，那我们已经找到了对
     *  应解，并立即将其返回。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        int[] result=new int[2];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],i);
            if (map.get(target-nums[i])!=null&&map.get(target-nums[i])!=i){
                result[0]=map.get(target-nums[i]);
                result[1]=i;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums={2, 7, 11, 15};
        int target=9;
        System.out.println(new Solution().twoSum(nums,target));
    }
}
