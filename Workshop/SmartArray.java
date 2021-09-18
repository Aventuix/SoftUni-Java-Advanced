package SoftUni.JavaAdavanced.Workshop;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SmartArray {
    final int INITIAL_CAPACITY = 4;

    private int[] elements;
    private int size;

    public SmartArray() {
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void forEach(Consumer<Integer> consumer) {
        IntStream.range(0, this.size)
                .forEach(i -> consumer.accept(elements[i]));
    }

    public void add(int element) {
        if (size == elements.length) {
            elements = grow();
        }
        this.elements[size++] = element;

    }

    private int[] grow() {
        int[] newElements = new int[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        return newElements;
    }

    public int get(int index) {
        ensureTheIndex(index);
        return this.elements[index];

    }

    public int size() {
        return this.size;
    }

    public boolean contains(int element) {
        return IntStream.range(0, this.size)
                .anyMatch(i -> elements[i] == element);
    }

    public int remove(int index) {
        ensureTheIndex(index);
        int element = get(index);

        if (this.size - 1 - index >= 0) {
            shiftLeft(index);
        }
        elements[--this.size] = 0;
        if (this.size == 0) {
            this.elements = new int[INITIAL_CAPACITY];
        }
        if (this.size < elements.length / 4 && elements.length > INITIAL_CAPACITY) {
            this.elements = shrink();
        }
        return element;
    }

    public void add(int index, int element) {
        ensureTheIndex(index);
        int lastElement = elements[this.size - 1];
        shiftRight(index);
        elements[index] = element;
        add(lastElement);
    }

    private void shiftLeft(int index) {
        System.arraycopy(elements, index + 1, elements, index, this.size - 1 - index);
    }

    private void shiftRight(int index) {
        System.arraycopy(elements, index, elements, index + 1, this.size - 1 - index);
    }

    private int[] shrink() {
        int[] newArray = new int[elements.length / 2];
        System.arraycopy(elements, 0, newArray, 0, this.size);
        return newArray;
    }

    private void ensureTheIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
