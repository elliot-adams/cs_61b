/*
To do:
Resizing method
Usage ratio implementation
 */

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    //Creates an empty list
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    //must take constant time unless resizing (no need to manage usage ratio here; usage going up and will necessarily be above 25% post-resize if resizing)
    //not resizeable
    public void addFirst(T x){
        if (size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = x;
        size ++;
        //if x is added at 0 (and there is still room at size - 1) need to send nextFirst to size - 1 (ie have never called addFirst but have removedFirst)
        if(nextFirst == 0){
            nextFirst = items.length - 1;
        }
        else{
            nextFirst --;
        }
    }

    //must take constant time unless resizing
    //not resizeable
    public void addLast(T x){
        if (size == items.length){
            resize(size * 2);
        }
        items[nextLast] = x;
        size ++;
        //if x is added at size - 1 (and there is still room at 0) need to send nextLast to 0 (ie have never called addLast but have removedLast)
        if(nextLast == items.length - 1){
            nextLast = 0;
        }
        else{
            nextLast ++;
        }
    }

    public boolean isEmpty(){
        //if you didn't want to trust size you could loop through starting at 0 and check if any items != null
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.print("Empty deque ");
        } else {
            int external_index = 0;
            while (external_index < size) {
                System.out.print(get(external_index) + " ");
                external_index++;
            }
        }
        System.out.println("(size: " + size +")"); //just for reference when checking
    }

    public T get(int i){
            if (i >= size || isEmpty()) {
                return null;
            }
            //accounting for circularity in constant time (not looping like printDeque)
            else{
                int first = nextFirst + 1;
                if (i + first > items.length - 1){
                    return items[i + first - items.length];
                }
                else{
                    return items[i + first];
                }
            }
    }

    //must take constant time unless resizing
    //should avoid loitering and manage usage ratio
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        //if nextFirst at last spot on right ie first item is at index 0
        if (nextFirst + 1 > items.length - 1){
            T removed = items[0];
            items[0] = null;
            nextFirst = 0;
            size --;
            return removed;
        }
        else{
            T removed = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst ++;
            size --;
            return removed;
        }
    }

    //must take constant time unless resizing
    //should avoid loitering and manage usage ratio
    public T removeLast(){
        if (isEmpty()){
            return null;
        }

        else{
            if(usage_ratio() < 0.35){
                cull(size);
            }

        //if nextLast at index 0 ie last item is at index size - 1
            if (nextLast == 0){
                T removed = items[items.length - 1];
                items[items.length - 1] = null;
                nextLast = items.length - 1;
                size --;
                return removed;
            }
            else{
                T removed = items[nextLast - 1];
                items[nextLast - 1] = null;
                nextLast --;
                size --;
                return removed;
            }
        }
    }

    /** Resizes the underlying array to the target capacity */
    private void resize(int capacity){
        //Note this method does not adjust the size variable itself
        T[] new_items = (T[]) new Object[capacity];

        int external_index = 0;
        while (external_index < size) {
            new_items[external_index] = get(external_index);
            external_index++;
        }
        nextFirst = capacity - 1; //last slot
        nextLast = size;
        items = new_items;
    }

    private double usage_ratio(){
        return size / items.length;
    }

    private void cull(int capacity){
        T[] new_items = (T[]) new Object[capacity];

        int external_index = 0;
        while (external_index < size) {
            new_items[external_index] = get(external_index);
            external_index++;
        }
        nextFirst = capacity - 1; //last slot
        nextLast = size;
        items = new_items;
    }

}
