package zs.slg.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀数  利用map实现
 */
public class TrieByMap {

    Node root;

    public static class Node {
        int pass;
        int end;
        Map<Integer,Node> next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }
    }

    public TrieByMap(){
        root = new Node();
    }

    public void insert(String word) {
        if (word == null) return;
        char[] chars = word.toCharArray();
        Node cur = root;
        cur.pass++;
        for (int i = 0; i < chars.length; i++) {
            if (!cur.next.containsKey((int)chars[i])){
                cur.next.put((int) chars[i],new Node());
            }
            cur = cur.next.get((int) chars[i]);
            cur.pass++;
        }
        cur.end++;
    }


    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) return;
        char[] chars = word.toCharArray();
        Node cur = root;
        cur.pass--;
        for (int i = 0; i < chars.length; i++) {
            if (--cur.next.get((int) chars[i]).pass == 0){
                cur.next.remove((int) chars[i]);
                return;
            }
            cur = cur.next.get((int) chars[i]);
        }
        cur.end--;
    }


    public int countWordsEqualTo(String word) {
        if (word == null) return 0;
        char[] chars = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (!cur.next.containsKey((int)chars[i])) return 0;
            cur = cur.next.get((int) chars[i]);
        }
        return cur.end;
    }


    public int countWordsStartingWith(String pre) {
        if (pre == null) return 0;
        char[] chars = pre.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (!cur.next.containsKey((int)chars[i])) return 0;
            cur = cur.next.get((int) chars[i]);
        }
        return cur.pass;
    }
}
