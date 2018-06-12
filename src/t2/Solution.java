package t2;


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        return addTwoNumbers1(l1,l2);
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int flag=0;//进位标志
        ListNode p1=l1,p2=l2;
        ListNode result=new ListNode(0);
        ListNode p3=result;
        //两条链表都未遍历时
        while (p1!=null&&p2!=null){
            if (flag+p1.val+p2.val>=10){
                p3.next=new ListNode(flag+p1.val+p2.val-10);
                flag=1;
            }else{
                p3.next=new ListNode(flag+p1.val+p2.val);
                flag=0;
            }
            if(p1!=null){
                p1=p1.next;
            }
            if(p2!=null){
                p2=p2.next;
            }
            p3=p3.next;
        }
        //l2遍历结束
        while (p1!=null){
            if (flag+p1.val>=10){
                p3.next=new ListNode(flag+p1.val-10);
                flag=1;
            }else{
                p3.next=new ListNode(flag+p1.val);
                flag=0;
            }
            p1=p1.next;
            p3=p3.next;
        }
        //l1遍历结束
        while (p2!=null){
            if (flag+p2.val>=10){
                p3.next=new ListNode(flag+p2.val-10);
                flag=1;
            }else{
                p3.next=new ListNode(flag+p2.val);
                flag=0;
            }
            p2=p2.next;
            p3=p3.next;
        }
        if(flag==1){
            p3.next=new ListNode(flag);
        }else{
            p3=null;
        }
        return result.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int flag=0;//进位标志
        ListNode p1=l1,p2=l2;
        ListNode result=new ListNode(0);
        ListNode p3=result;
        while (p1!=null||p2!=null){
            int x=p1==null?0:p1.val;
            int y=p2==null?0:p2.val;
            int sum=x+y+flag;
            p3.next=new ListNode(sum%10);
            flag=sum/10;
            p1=p1.next;
            p2=p2.next;
            p3=p3.next;
        }
        return result.next;
    }

    public static void main(String[] args) {

    }
}
