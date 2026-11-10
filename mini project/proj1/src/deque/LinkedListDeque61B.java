package deque;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque61B(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(){
            prev = null;
            item = null;
            next = null;
        }
    }

    @Override
    public void addFirst(T x) {
        Node p = new Node();

        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        p.prev = sentinel;

        p.item = x;
        size+=1;
    }

    @Override
    public void addLast(T x) {
        Node p = new Node();

        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        sentinel.prev = p;
        p.next = sentinel;

        p.item = x;
        size+=1;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if(size != 0){
            Node<T> p = sentinel.next;
            while(p!=sentinel){
                returnList.add(p.item);
                p=p.next;
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getFirst() {
        return sentinel.next.item;
    }

    @Override
    public T getLast() {
        return sentinel.prev.item;
    }

    @Override
    public T removeFirst() {
        T returnitem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;

        return returnitem;
    }

    @Override
    public T removeLast() {
        T returnitem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        return returnitem;
    }

    @Override
    public T get(int index) {
        if(index > size-1 || size < 0){
            return null;
        }
        Node<T> p = sentinel;
        for(int i = 0;i<=index;i++){
            p=p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index > size-1){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
    }

    public T getRecursiveHelper(Node<T> p,int index){
        if(index == 0){
            return p.item; //这里我犯了一个严重错误，写成了sentinel.next.item，没有理会helper函数的辅助意思
        }
        return getRecursiveHelper(p.next,index-1);
    }

    public void main (String[] args){

    }
}
