package zs.slg.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 *
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树
 * 树的头节点是公司唯一的老板，除老板之外的每个员工都有唯一的直接上级
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外每个员工都有一个或多个直接下级
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值
 */
public class MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static class Info{
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    public static int maxHappy(Employee head) {
        if (head == null) return 0;
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public static Info process(Employee x) {
        if (x == null) return new Info(0,0);

        int yes = x.happy;
        int no = 0;
        for (Employee e : x.nexts) {
            Info info = process(e);
            yes += info.no;   // x 来, x 的下属不来
            no += Math.max(info.no, info.yes); // x 不来    Max(直接下属来的最大收益,直接下属不来的最大收益)
        }
        return new Info(no, yes);
    }
}
