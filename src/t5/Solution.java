package t5;

public class Solution {
    public String longestPalindrome(String s) {
        return longestPalindrome1(s);
    }

    /**
     * 思路：
     *      依次遍历整个字符串，把每个字符作为回文子串的中心点，同时向左右扩展直至左右两端字符不同位置。
     *      这样向两端扩展时要分为两种情况：一种是某位置的字符恰好是回文子串正中心，另一种是该字符是回文子串中心两
     *  个字符之一。
     * 时间复杂度：O(n*(n+n))~O(n^2)
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        String result="";
        StringBuffer current=null;
        int len=s.length();
        for (int i=0;i<len;i++){
            current=new StringBuffer();
            current.append(s.charAt(i));
            for (int j=1;i-j>=0&&i+j<len;j++){//i位置字符为正中心时
                if(s.charAt(i+j)==s.charAt(i-j)){
                    current.insert(0,s.charAt(i-j));
                    current.append(s.charAt(i-j));
                }else{
                    break;
                }
            }
            result=current.length()>result.length()?current.toString():result;

            current=new StringBuffer();
            current.append(s.charAt(i));
            if(i+1<len&&s.charAt(i)==s.charAt(i+1)){//字符i与字符i+1相同时考虑第二种情况，否则无需考虑
                current.append(s.charAt(i));
                for (int j=1;i-j>=0&&i+1+j<len;j++){
                    if(s.charAt(i-j)==s.charAt(i+1+j)){
                        current.append(s.charAt(i-j));
                        current.insert(0,s.charAt(i-j));
                    }else{
                        break;
                    }
                }
            }
            result=current.length()>result.length()?current.toString():result;
        }
        return result;
    }

    public static void main(String[] args) {
        String s="babaddtattarrattatddetartrateedredividerb";
        System.out.println(new Solution().longestPalindrome(s));
    }
}
