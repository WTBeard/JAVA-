package zs.slg.heap;

import java.util.*;

/**
 * 加强堆
 */
public class HeapGreater<T> {

    List<T> heap;
    Map<T, Integer> indexMap;
    int heapSize;
    Comparator<? super T> comparator;

    HeapGreater(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        if (size() == 0) throw new RuntimeException("堆为空");
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public void heapInsert(int size) {
        while (comparator.compare(heap.get((size - 1) / 2), heap.get(size)) > 0) {
            swap((size - 1) / 2, size);
            size = (size - 1) / 2;
        }
    }

    public void swap(int i, int j) {
        if (i == j) return;
        T ti = heap.get(i);
        T tj = heap.get(j);
        heap.set(i, tj);
        heap.set(j, ti);
        indexMap.put(ti, j);
        indexMap.put(tj, i);
    }

    public T pop() {
        if (size() == 0) throw new RuntimeException("堆为空");
        T res = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(res);
        heap.remove(--heapSize);
        heapIfy(0);
        return res;
    }

    public void heapIfy(int size) {
        int left = size * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && comparator.compare(heap.get(left+1),heap.get(left)) > 0 ? left + 1:left;
            largest = comparator.compare(heap.get(largest),heap.get(size)) > 0 ? left:size;
            if (largest == size) break;
            swap(size,largest);
            size = largest;
            left = size * 2 + 1;
        }
    }

    public void remove(T obj) {
        if (size() == 0) throw new RuntimeException("堆为空");
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        T t = heap.get(heapSize - 1);
        heap.remove(--heapSize);
        if (t != null){
            heap.set(index,t);
            indexMap.put(t,index);
            resign(t);
        }
    }

    public void resign(T obj) {
        Integer index = indexMap.get(obj);
        heapIfy(index);
        heapInsert(index);
    }


    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }
}
