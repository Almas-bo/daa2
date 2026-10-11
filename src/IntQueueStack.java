public class IntQueueStack implements IntStack {
    private IntLinkedListQueue main = new IntLinkedListQueue();
    private IntLinkedListQueue other = new IntLinkedListQueue();

    @Override
    public void push(int value) {
        other.enqueue(value);
        while (!main.isEmpty()) other.enqueue(main.dequeue());
        IntLinkedListQueue tmp = main;
        main = other;
        other = tmp;
    }

    @Override
    public int pop() {
        if (isEmpty()) throw new RuntimeException("stack is empty");
        return main.dequeue();
    }

    @Override
    public int peek() {
        if (isEmpty()) throw new RuntimeException("stack is empty");
        return main.peek();
    }

    @Override
    public int size() {
        return main.size();
    }

    @Override
    public boolean isEmpty() {
        return main.isEmpty();
    }

    @Override
    public String toString() {
        int n = main.size();
        int[] items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = main.dequeue();
            main.enqueue(items[i]);
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = n - 1; i >= 0; i--) {
            sb.append(items[i]);
            if (i > 0) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
