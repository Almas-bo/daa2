public class IntLinkedList {
    Node head;
    Node tail;
    int size;

    static class Node {
        int value;
        Node next;
        Node prev;
        Node() {
            value = 0;
            next = null;
            prev = null;
        }

        Node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }

        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public IntLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private Node nodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index " + index);
        Node cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }

    public void add(int value) {
        Node node = new Node(value, tail, null);
        if (tail == null) head = node;
        else tail.next = node;
        tail = node;
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index " + index);
        if (index == size) {
            add(value);
            return;
        }
        Node next = nodeAt(index);
        Node node = new Node(value, next.prev, next);
        if (next.prev == null) head = node;
        else next.prev.next = node;
        next.prev = node;
        size++;
    }

    public int get(int index) {
        return nodeAt(index).value;
    }

    public void set(int index, int value) {
        nodeAt(index).value = value;
    }

    public int remove(int index) {
        Node node = nodeAt(index);
        if (node.prev == null) head = node.next;
        else node.prev.next = node.next;
        if (node.next == null) tail = node.prev;
        else node.next.prev = node.prev;
        size--;
        return node.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur != head) sb.append(", ");
            sb.append(cur.value);
        }
        return sb.append("]").toString();
    }
}
