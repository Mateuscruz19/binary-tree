package Structures;

public class Stack<T>{
    private Node<T> head;
    private int size;

        public Stack(){
            this.head = null;
            this.size = 0;
        }

        public void push(T e){
            Node<T> newItem = new Node<T>(e);
            newItem.next = this.head;
            this.head = newItem;
            size++;
        }

        public T pop(){
            if(this.head == null){
                return null;
            }
            else{
                Node<T> temp = this.head;
                this.head = this.head.next;
                size--;
                return temp.value;
            }
        }

        public T top(){
            if(this.head == null){
                return null;
            }
            return this.head.value;
        }

        public Boolean isEmpty(){
            return this.size == 0;
        }

        public int size(){
            return this.size;
        }

        public void clear(){
            this.head = null;
            this.size = 0;
        }

        public MyArrayList<T> toArray(){
            MyArrayList<T> StackArray = new MyArrayList<T>(this.size);
            Node<T> current = this.head;
            while(current != null){
                StackArray.add(current.value);
                current = current.next;
            }
            return StackArray;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            Node<T> current = this.head;
            while(current != null){
                sb.append(current.value + " ");
                current = current.next;
            }
            return sb.toString();
        }
}
