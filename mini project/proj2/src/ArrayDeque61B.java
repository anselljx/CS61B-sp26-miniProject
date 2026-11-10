import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int maxSize;

    public ArrayDeque61B (){
        maxSize = 8;
        items = (T[]) new Object[maxSize];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    @Override
    public void addFirst(T x) {
        if(size > maxSize){
            return;
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst + maxSize - 1) % (maxSize);
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size > maxSize){
            return;
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % (maxSize);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0;i < size;i++){
            int pos = (nextFirst + 1 + i) % (maxSize);
            T item = items[pos];
            if(item != null) {
                returnList.add(item);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getFirst() {
        if(this.isEmpty()){
            return null;
        }

        int pos = (nextFirst + 1) % (maxSize);
        return items[pos];
    }

    @Override
    public T getLast() {
        if(this.isEmpty()){
            return null;
        }

        int pos = (nextLast - 1 + maxSize) % (maxSize);
        return items[pos];
    }

    @Override
    public T removeFirst() {
        if(this.isEmpty()){
            return null;
        }

        int pos = (nextFirst + 1) % (maxSize);
        T returnitem = items[pos];
        items[pos] = null;
        nextFirst = pos;
        size--;

        return returnitem;
    }

    @Override
    public T removeLast() {
        if(this.isEmpty()){
            return null;
        }

        int pos = (nextLast - 1 + maxSize) % (maxSize);
        T returnitem = items[pos];
        items[pos] = null;
        nextLast = pos;
        size--;

        return returnitem;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0){
            return null;
        }
        int pos = (nextFirst + index + 1) % (maxSize);
        return items[pos];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }



    public void resize(int newSize){
        T[] newitems = (T[]) new Object[newSize];

        for(int i = 0;i < size; i++){
            newitems[i]=this.get(i);
        }

        this.nextFirst = newSize - 1;
        this.nextLast = size;

        maxSize = newSize;
        items = newitems;
    }





}
