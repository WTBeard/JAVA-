package zs.slg.linknode;

/**
 * 在链表中删除指定值的所有节点
 */
public class DeleteGivenValue {

    public static Node removeValue(Node head,int v){
        if (head == null) return head;

        // 首先判断 头节点 是否是需要删除的节点
        while (head != null && head.value == v){
            head = head.next;
        }

        Node per = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == v){
                // 需要删除的节点，直接让 上一个节点的指针指向当前节点的下一个节点 （不搭理当前节点）
                per.next = cur.next;
            }else {
                // 不需要删除的节点， 上一个节点设为当前节点 （针对下一次循环来说）
                per = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
