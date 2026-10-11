public class IntStackQueue implements IntQueue {
    private IntArrayStack in;
    private IntArrayStack out;

    public IntStackQueue() {
        in = new IntArrayStack();
        out = new IntArrayStack();
    }

    private void shift() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.push(in.pop());
        }
    }

    @Override
    public void enqueue(int value) {
        in.push(value);
    }

    @Override
    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("queue is empty");
        shift();
        return out.pop();
    }

    @Override
    public int peek() {
        if (isEmpty()) throw new RuntimeException("queue is empty");
        shift();
        return out.peek();
    }

    @Override
    public int size() {
        return in.size() + out.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        IntArrayStack tmp = new IntArrayStack();
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        while (!out.isEmpty()) {
            int v = out.pop();
            tmp.push(v);
            if (!first) sb.append(", ");
            sb.append(v);
            first = false;
        }
        while (!tmp.isEmpty()) out.push(tmp.pop());
        String inPart = in.toString();
        inPart = inPart.substring(1, inPart.length() - 1);
        if (!inPart.isEmpty()) {
            if (!first) sb.append(", ");
            sb.append(inPart);
        }
        return sb.append("]").toString();
    }
}
