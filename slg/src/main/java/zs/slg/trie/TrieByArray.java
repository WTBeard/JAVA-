package zs.slg.trie;

/**
 * 前缀数  利用数组实现
 */
public class TrieByArray {

    Node root;

    public static class Node {
        int pass;
        int end;
        Node[] next;

        public Node(int pass, int end) {
            this.pass = pass;
            this.end = end;
            this.next = new Node[26];
        }
    }

    TrieByArray() {
        root = new Node(0, 0);
    }


    public void insert(String word) {
        if (word == null) return;

        char[] chars = word.toCharArray();
        Node cur = root;
        cur.pass++;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node(0 , 0);
            }
            cur = cur.next[index];
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
            int index = chars[i] - 'a';
            if (--cur.next[index].pass == 0){
                cur.next[index] = null;
                return;
            }
            cur = cur.next[index];
        }
        cur.end--;
    }


    public int countWordsEqualTo(String word) {
        if (word == null) return 0;

        char[] chars = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (cur.next[index] == null) return 0;
            cur = cur.next[index];
        }
        return cur.end;
    }


    public int countWordsStartingWith(String pre) {
        if (pre == null) return 0;

        char[] chars = pre.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (cur.next[index] == null) return 0;
            cur = cur.next[index];
        }
        return cur.pass;
    }
}
