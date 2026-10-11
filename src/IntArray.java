public class IntArray {
    private int[] data;
    private int size;

    public IntArray() {
        data = new int[4];
        size = 0;
    }

    private void grow() {
        if (size == data.length) {
            int[] bigger = new int[data.length * 2];
            for (int i = 0; i < size; i++) bigger[i] = data[i];
            data = bigger;
        }
    }

    private void check(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index " + index);
    }

    public void add(int value) {
        grow();
        data[size++] = value;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index " + index);
        grow();
        for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = value;
        size++;
    }

    public int get(int index) {
        check(index);
        return data[index];
    }

    public void set(int index, int value) {
        check(index);
        data[index] = value;
    }

    public int remove(int index) {
        check(index);
        int removed = data[index];
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        size--;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int[] values) {
        for (int v : values) add(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[i]);
        }
        return sb.append("]").toString();
    }
}
