public class IntArrayDeque implements IntDeque {
    private IntArray buffer;
    private int front;
    private int size;

    public IntArrayDeque() {
        buffer = new IntArray();
        for (int i = 0; i < 4; i++) buffer.add(0);
        front = 0;
        size = 0;
    }

    private int index(int i) {
        return (front + i) % buffer.size();
    }

    private void grow() {
        if (size < buffer.size()) return;
        int oldCapacity = buffer.size();
        IntArray bigger = new IntArray();
        for (int i = 0; i < size; i++) bigger.add(buffer.get(index(i)));
        for (int i = size; i < oldCapacity * 2; i++) bigger.add(0);
        buffer = bigger;
        front = 0;
    }

    @Override
    public void addFirst(int value) {
        grow();
        front = (front - 1 + buffer.size()) % buffer.size();
        buffer.set(front, value);
        size++;
    }

    @Override
    public void addLast(int value) {
        grow();
        buffer.set(index(size), value);
        size++;
    }

    @Override
    public int removeFirst() {
        if (isEmpty()) throw new RuntimeException("deque is empty");
        int value = buffer.get(front);
        front = (front + 1) % buffer.size();
        size--;
        return value;
    }

    @Override
    public int removeLast() {
        if (isEmpty()) throw new RuntimeException("deque is empty");
        int value = buffer.get(index(size - 1));
        size--;
        return value;
    }

    @Override
    public int peekFirst() {
        if (isEmpty()) throw new RuntimeException("deque is empty");
        return buffer.get(front);
    }

    @Override
    public int peekLast() {
        if (isEmpty()) throw new RuntimeException("deque is empty");
        return buffer.get(index(size - 1));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(buffer.get(index(i)));
        }
        return sb.append("]").toString();
    }
}
