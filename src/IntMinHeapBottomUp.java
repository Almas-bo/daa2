public class IntMinHeapBottomUp implements IntQueue {
    private IntArray heap;

    public IntMinHeapBottomUp() {
        heap = new IntArray();
    }

    public IntMinHeapBottomUp(int[] values) {
        heap = new IntArray();
        heap.add(values);
        for (int i = heap.size() / 2 - 1; i >= 0; i--) siftDown(i);
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    private void siftUp(int i) {
        while (i > 0 && heap.get(i) < heap.get((i - 1) / 2)) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void siftDown(int i) {
        int n = heap.size();
        while (true) {
            int smallest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && heap.get(l) < heap.get(smallest)) smallest = l;
            if (r < n && heap.get(r) < heap.get(smallest)) smallest = r;
            if (smallest == i) return;
            swap(i, smallest);
            i = smallest;
        }
    }

    @Override
    public void enqueue(int value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    @Override
    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    @Override
    public int peek() {
        if (isEmpty()) throw new RuntimeException("heap is empty");
        return heap.get(0);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
