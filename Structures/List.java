package Structures;

public class List<T> {
    Node<T> base;
    Node<T> top;
    int size;

    public List() {
        this.base = null;
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (this.base == null) {
            this.base = newNode;
            this.top = newNode;
        } else {
            newNode.prev = this.top;
            this.top.next = newNode;
            this.top = newNode;
        }
        this.size++;
    }

    public void add(int pos, T value) {
        if (pos < 0 || pos > this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + pos);
        }
        Node<T> newNode = new Node<>(value);

        if (pos == 0) {
            if (this.base == null) {
                this.base = newNode;
                this.top = newNode;
            } else {
                newNode.next = this.base;
                this.base.prev = newNode;
                this.base = newNode;
            }
        } else if (pos == this.size) {
            newNode.prev = this.top;
            this.top.next = newNode;
            this.top = newNode;
        } else {
            Node<T> current = getNode(pos);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        this.size++;
    }

    public T remove(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + pos);
        }
        Node<T> target = getNode(pos);
        return remove(target);
    }

    public T remove(Node<T> node) {
        T removed = node.value;

        if (node == this.base && node == this.top) {
            this.base = null;
            this.top = null;
        } else if (node == this.base) {
            this.base = node.next;
            this.base.prev = null;
        } else if (node == this.top) {
            this.top = node.prev;
            this.top.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        this.size--;
        return removed;
    }

    public Node<T> getNode(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + pos);
        }
        Node<T> current = this.base;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        return current;
    }

    public T get(int pos) {
        return getNode(pos).value;
    }

    public void set(int pos, T value) {
        getNode(pos).value = value;
    }

    public int find(T value) {
        Node<T> current = this.base;
        for (int i = 0; i < this.size; i++) {
            if (current.value.equals(value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = this.base;
        while (current != null) {
            sb.append(current.value + " ");
            current = current.next;
        }
        return sb.toString();
    }
}
