package Structures;

public class MyArrayList<T> {
    T[] elements;
    int capacity;
    int size;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = (T[]) new Object[capacity];
    }

    public Boolean add(T element){
        if(size == capacity){
            grow();
        }
        elements[size++] = element;
        return true;
    }

    public void grow() {
        this.capacity += this.capacity / 2;
        T[] newElements = (T[]) new Object[this.capacity];
        for (int i = 0; i < this.size; i++) {
            newElements[i] = this.elements[i];
        }
        this.elements = newElements;
    }

    public Boolean add(int index, T element){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if(size == capacity){
            grow();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
        return true;
    }

    public Boolean remove(int index){
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return true;
    }

    public Boolean remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public Boolean set(int index, T element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        elements[index] = element;
        return true;
    }

    public T get(int index){
        return this.elements[index];
    }

    public Boolean contains(T element){
        for(int i = 0; i < this.size;i++){
            if(elements[i] == element){
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element){
        for(int i = 0; i < this.size;i++){
            if(elements[i] == element){
                return i;
            }
        }
        return -1;
    }

    public T[] toArray(){
        T[] staticArray = (T[]) new Object[this.elements.length];
        int i = 0;
        while (i < this.size){
            staticArray[i] = this.elements[i];
            i++;
        }
        return staticArray;
    }

}
