package Structures;

public class Queue<T> {
    Node<T> first;
    Node<T> top;
    int size;

        public Queue() {
            this.first = null;
            this.top = null;
            this.size = 0;
        }

        public void enqueue(T e){
           Node<T> newItem = new Node<>(e);
           if(this.first == null){
               this.first = newItem;
           } else {
               this.top.next = newItem;
           }
           this.top = newItem;
           this.size++;
        }

        public T dequeue(){
            if(this.first == null){
                return null;
            }
            Node<T> removedValue = this.first;
            this.first = this.first.next;
            if(this.size == 1){
                this.top = this.first;
            }
            this.size--;
            return removedValue.value;
        }

        public T front(){
            if(this.first == null){
                return null;
            }
            return this.first.value;
        }

        public Boolean isEmpty(){
            return this.size == 0;
        }

        public int size(){
            return this.size;
        }

        public void clear(){
            this.first = null;
            this.top = null;
            this.size = 0;
        }

        public MyArrayList<T> toMyArray(){
            MyArrayList<T> QueueArray = new MyArrayList<T>(this.size);
            Node<T> current = this.first;
            for(int i = 0; i < this.size; i++){
                QueueArray.add(current.value);
                current = current.next;
            }
            return QueueArray;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            Node<T> current = this.first;
            while(current != null){
                sb.append(current.value + " ");
                current = current.next;
            }
            return sb.toString();
        }


}
