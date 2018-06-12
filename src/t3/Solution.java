package t3;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstring1(s);
    }

    /**
     * 思路：
     * 与求数组最大连续和求法类似，可用动态规划的方法求解
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

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("dvdc"));
    }
}
