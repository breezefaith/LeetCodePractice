package t4;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMedianSortedArrays2(nums1,nums2);
    }

    /**
     * 思路：
     *      采用合并排序算法中合并的方法，但只需将数组合并到中位数的位置即可，令midPos=(nums1.length+nums2.length)/2,
     *  则中位数的下标应该为merge(midPos)（共奇数个元素），或者(merge[midPos]+merge[midPos-1])/2.0（共偶数个元素）。
     *
     * 时间复杂度：O((m+n)/2)
     * 空间复杂度：O((m+n)/2)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        double result=0;
        int midPos=(nums1.length+nums2.length)/2;
        int[] merge=new int[midPos+1];
        int i=0,j=0,k=0;
        while (k<=midPos){
            if (i>=nums1.length&&j<nums2.length){//nums1读取结束而nums2未结束
                merge[k++]=nums2[j++];
                continue;
            }
            if (i<nums1.length&&j>=nums2.length){//nums2读取结束nums2未结束
                merge[k++]=nums1[i++];
                continue;
            }
            if(nums1[i]<nums2[j]){
                merge[k++]=nums1[i++];
            }else{
                merge[k++]=nums2[j++];
            }
        }
        if ((nums1.length+nums2.length)%2==0){
            return (merge[midPos]+merge[midPos-1])/2.0;
        }else{
            return (double)merge[midPos];
        }
    }

    /**
     * 思路：
     *      看到log(m+n)的时间要求，很自然的就会联想到二分查找算法，因此我们可以沿着这条思路进行尝试。下面尝试从两个数组中
     *  寻找两个点nums1[i]和nums2[j]，使得num1[i]<nums1[i+1],nums2[j]<nums2[j+1]，且num1[i]<nums2[j+1],
     *  nums2[j]<nums1[j+1]。实际上这样就转化为了关于两个数组的第k小问题，只是当总数为奇数时求第k小的数，为偶数时求k和k+1
     *  的平均值。
     *
     *      思想与二分查找思想一样，每次可以去掉k/2的值， k = (m + n) / 2;该方法的核心是将原问题转换为一个寻找第k小数的问
     *  题，这样中位数实际上是第（m + n）/2小的数。因此本质问题是求解第k小数的问题。
     *      首先假设数组A和B的元素个数都大于k/2，我们比较A[k/2-1]和B[k/2-1]两个元素，这两个元素分别表示A的第k/2小的元素和
     *  B的第k/2小的元素。这两个元素比较共有三种情况：>、<和=。若A[k/2-1]>B[k/2-1]，则所求在A[0...k/2-1]和B[k/2...n]中；
     *  若A[k/2-1]<B[k/2-1]，则所求在A[k/2...m]和B[0...k/2-1]中；A[k/2-1]=B[k/2-1]，则A[k/2-1]即为第k小元素
     *      证明也很简单，可以采用反证法。假设A[k/2-1]大于合并之后的第k小值，我们不妨假定其为第（k+1）小值。由于A[k/2-1]小
     *  于B[k/2-1]，所以B[k/2-1]至少是第（k+2）小值。但实际上，在A中至多存在k/2-1个元素小于A[k/2-1]，B中也至多存在k/2-1
     *  个元素小于A[k/2-1]，所以小于A[k/2-1]的元素个数至多有k/2+k/2-2=k-2<k，这与A[k/2-1]是第（k+1）的数矛盾。同理当
     *  A[k/2-1]>B[k/2-1]时存在类似的结论。当A[k/2-1]=B[k/2-1]时，表示，在在A的k/2-1之前已经有k/2-1和数小于A[k/2-1]，
     *  同理在B之前也是一样的，所以此时已经找到了第k小的数，即这个相等的元素。
     *
     * 时间复杂度：O(log(m+n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1=nums1.length;
        int len2=nums2.length;
        int size=len1+len2;
        if((size%2)==1){//总大小为奇数
            return findKth(nums1,nums2,0,len1,0,len2,size/2+1);
        }else{
            return (findKth(nums1,nums2,0,len1,0,len2,size/2)
                    +findKth(nums1,nums2,0,len1,0,len2,size/2+1))/2.0;
        }

    }

    private double findKth(int[] nums1, int[] nums2, int left1, int len1, int left2, int len2, int k) {
        if(len1-left1>len2-left2){//保证nums1为较短数组
            return findKth(nums2,nums1,left2,len2,left1,len1,k);
        }
        if (len1-left1==0){//表示num1中的数已经全部位于前k个数中，则第k个数为nums2[k-1]
            return nums2[k-1];
        }
        if (k==1){//表示已经找到第k-1个数，第k个数即为num1和nums2中当前起始位置中较小的一个
            return Math.min(nums1[left1],nums2[left2]);
        }
        int p1=left1+Math.min(len1-left1,k/2);
        int p2=left2+k-(p1-left1);
        if (nums1[p1-1]<nums2[p2-1]){
            return findKth(nums1,nums2,p1,len1,left2,len2,k-p1+left1);
        }else if (nums1[p1-1]>nums2[p2-1]){
            return findKth(nums1,nums2,left1,len1,p2,len2,k-p2+left2);
        }else {
            return nums1[p1-1];
        }
    }

    public static void main(String[] args) {
        int[] nums1={2};
        int[] nums2={};
        System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));
    }
}
