package SoftUni.JavaAdavanced.Workshop;

import java.util.function.Consumer;

public class Stack {
    private static class Node {
        private int element;
        private Node prev;

        public Node(int element) {
            this.element = element;
        }

    }

    private int size;
    private Node top;

    public Stack() {
    }

    public void push(int element) {
        Node newNode = new Node(element);
        if (this.top != null) {
            newNode.prev = this.top;
        }
        this.top = newNode;
        this.size++;
    }

    public int pop() {
        ensureNonEmpty();
        int popped = this.top.element;
        this.top = this.top.prev;
        this.size--;
        return popped;
    }

    private void ensureNonEmpty() {
        if (this.top == null) {
            throw new IllegalStateException("Empty Stack");
        }
    }

    public int size() {
        return this.size;
    }

    public int peek() {
        ensureNonEmpty();
        return this.top.element;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void forEach(Consumer<Integer> consumer) {
        Node current = this.top;

        while (current != null) {
            consumer.accept(current.element);
            current = current.prev;
        }
    }
}
