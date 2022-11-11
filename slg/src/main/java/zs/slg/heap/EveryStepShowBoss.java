package zs.slg.heap;

import java.util.*;

/**
 * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr= [3,3,1,2,1,2,5…
 * op = [T,T,T,T,F,T,F…
 * 依次表示：
 * 3用户购买了一件商品
 * 3用户购买了一件商品
 * 1用户购买了一件商品
 * 2用户购买了一件商品
 * 1用户退货了一件商品
 * 2用户购买了一件商品
 * 5用户退货了一件商品…
 *
 * 一对arr[i]和op[i]就代表一个事件：用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品，op[i] == F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，都给购买次数最多的前K名用户颁奖。所以每个事件发生后，你都需要一个得奖名单（得奖区）。
 *
 * 得奖系统的规则：
 * 1，如果某个用户购买商品数为0，但是又发生了退货事件，则认为该事件无效，得奖名单和上一个事件发生后一致，例子中的5用户
 * 2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3，每次都是最多K个用户得奖，K也为传入的参数
 * 如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4，得奖系统分为得奖区和候选区，任何用户只要购买数>0，
 * 一定在这两个区域中的一个
 * 5，购买数最大的前K名用户进入得奖区，
 * 在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6，如果购买数不足以进入得奖区的用户，进入候选区
 * 7，如果候选区购买数最多的用户，已经足以进入得奖区，
 * 该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 * 如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 * 如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8，候选区和得奖区是两套时间，
 * 因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 * 从得奖区出来进入候选区的用户，得奖区时间删除，
 * 进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 从候选区出来进入得奖区的用户，候选区时间删除，
 * 进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，
 * 离开是指彻底离开，哪个区域也不会找到该用户
 * 如果下次该用户又发生购买行为，产生>0的购买数，
 * 会再次根据之前规则回到某个区域中，进入区域的时间重记
 * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 */
public class EveryStepShowBoss {

    Map<Integer,Customer> customerMap;
    HeapGreater<Customer> candHeap;
    HeapGreater<Customer> daddyHeap;
    final int k;

    public static class  Customer{
        int id;
        int buy;
        int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }
    }

    public EveryStepShowBoss(int limit) {
        customerMap = new HashMap<>();
        candHeap = new HeapGreater<>((o1, o2) -> {
            return o1.buy == o2.buy ? o1.enterTime - o2.enterTime:o2.buy - o1.buy; // 买的多,进的早
        });
        daddyHeap = new HeapGreater<>((o1, o2) -> {
            return o1.buy == o2.buy ? o1.enterTime - o2.enterTime:o1.buy - o2.buy; // 买的少,进的早
        });
        this.k = limit;
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> res = new ArrayList<>();
        EveryStepShowBoss everyStepShowBoss = new EveryStepShowBoss(k);
        for (int i = 0; i < arr.length; i++) {
            everyStepShowBoss.operate(i,arr[i],op[i]);
            res.add(everyStepShowBoss.getDaddies());
        }
        return res;
    }

    public List<Integer> getDaddies() {
        List<Customer> customers = daddyHeap.getAllElements();
        List<Integer> ans = new ArrayList<>();
        for (Customer c : customers) {
            ans.add(c.id);
        }
        return ans;
    }

    public void operate(int time, int id, boolean buyOrRefund) {
        if (!customerMap.containsKey(id) && !buyOrRefund) return;
        if (!customerMap.containsKey(id)){
            customerMap.put(id,new Customer(id,0,0));
        }
        Customer customer = customerMap.get(id);
        if (buyOrRefund){
            customer.buy++;
        }else {
            customer.buy--;
        }
        if (customer.buy == 0){
            customerMap.remove(id);
        }
        if (!candHeap.contains(customer) && !daddyHeap.contains(customer)){
            customer.enterTime = time;
            if (daddyHeap.size() < k){
                daddyHeap.push(customer);
            }else {
                candHeap.push(customer);
            }
        }else if (candHeap.contains(customer)){
            if (customer.buy == 0){
                candHeap.remove(customer);
            }else {
                candHeap.resign(customer);
            }
        }else {
            if (customer.buy == 0){
                daddyHeap.remove(customer);
            }else {
                daddyHeap.resign(customer);
            }
        }
        move(time);
    }

    public void move(int time){
        if (candHeap.isEmpty()) return;
        if (daddyHeap.size() < k){
            Customer customer = candHeap.pop();
            customer.enterTime = time;
            daddyHeap.push(customer);
        }else if (candHeap.peek().buy > daddyHeap.peek().buy){
            Customer can = candHeap.pop();
            can.enterTime = time;
            Customer dad = daddyHeap.pop();
            dad.enterTime = time;
            candHeap.push(dad);
            daddyHeap.push(can);
        }
    }
}
