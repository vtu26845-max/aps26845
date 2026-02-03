class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        ListNode result = null;
        for (int i = 0; i < lists.length ; i++) {
            result = mergeTwoLists(result,lists[i]);
        }
        return result;   
    }
    public static ListNode mergeTwoLists(ListNode n1, ListNode n2){
        ListNode temp = new ListNode(-1);
        ListNode tail = temp;
        while (n1 != null && n2 != null){
            if (n1.val < n2.val){
                tail.next = n1;
                n1 = n1.next;
            }else {
                tail.next = n2;
                n2 = n2.next;
            }
            tail = tail.next;
        }
        if (n1 != null) {
            tail.next = n1;
        }else {
            tail.next = n2;
        }
        return temp.next;
    }
}