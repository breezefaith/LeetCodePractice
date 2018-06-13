package t3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstring1(s);
    }

    /**
     * 思路：
     *      与求数组最大连续和求法类似，可用动态规划的方法求解。首先定义两个整型数组end与all，end[i]表示包含第i位的最
     *  长无重复子串的长度，all[i]表示第i位时最长无重复子串的长度，currentStr表示当前正在读的最长无重复子串,chars为
     *  将输入字符串转化为的字符数组。
     *      首先，若字符串s为空则直接返回0；否则end[0]与all[0]肯定为1。从i=1开始进行循环，从currentStr中查找chars[i],
     *  若不包含该字符，则currentStr追加该字符，end[i]长度为end[i-1]+1;若包含该字符，则判断字符chars[i]是否位于currentStr
     *  的最后一位，若是则直接清空当前子串（此处实现是让其指向一个新的StringBuffer对象），否则令currentStr指向currentStr的
     *  子串，其实位置是chars[i]在currentStr中的位置position+1。将chars[i]追加至更新后的currentStr中，end[i]是此时currentStr
     *  的长度。而all[i]则取all[i-1]与end[i]中的较大者。循环结束后all[s.length-1]即为我们所求的解。
     *
     * 时间复杂度：O(n^2)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        char[] chars=s.toCharArray();
        int []end=new int[chars.length];
        int []all=new int[chars.length];
        StringBuffer currentStr=new StringBuffer();
        end[0]=1;
        all[0]=1;
        currentStr.append(chars[0]);
        for (int i=1;i<chars.length;i++){
            int position=currentStr.indexOf(String.valueOf(chars[i]));
            if (position==-1){//当前最长子串不包含char[i]
                currentStr.append(chars[i]);
                end[i]=end[i-1]+1;
            }else{
                if (position!=currentStr.length()-1){
                    currentStr=new StringBuffer(currentStr.substring(position+1));
                }else{
                    currentStr=new StringBuffer();
                }
                currentStr.append(chars[i]);
                end[i]=currentStr.length();
            }
            all[i]=Integer.max(end[i],all[i-1]);
        }
        return all[chars.length-1];
    }

    /**
     * 思路：
     *      通过使用 HashSet 作为滑动窗口，我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。滑
     *  动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集
     *  合，即 [i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j)
     *  向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。
     *      回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。 然后我们
     *  向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。此时，
     *  我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。如果我们对所有的 ii 这样做，就可以得到答案。
     *  时间复杂度：O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int i=0,j=0,result=0;
        HashSet<Character> set=new HashSet<>();
        while (i<s.length()&&j<s.length()){
            if (set.contains(s.charAt(j))==true){
                set.remove(s.charAt(i++));
            }else{
                set.add(s.charAt(j++));
                result=Math.max(result,j-i);
            }
        }
        return result;
    }

    public int lengthOfLongestSubstring3(String s) {
        int i=0,j=0,result=0;
        Map<Character,Integer> map=new HashMap<>();//此处Map可以替换成字符数组char[128]
        for (i=0,j=0;j<s.length();j++){
            if (map.containsKey(s.charAt(j))){
                i=Math.max(map.get(s.charAt(j)),i);
            }
            result=Math.max(result,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("dvdc"));
    }
}
