public class AList<T>{
    /*
    in terms of a get i method, linked lists will be slower because they have to traverse to the ith item
    (however will revisit this later)
    Arrays don't need to traverse and can essentially grab any i in constant time

    Invariants:
    The number of items in the list should be size
    The next item we want to add will go into position size
    The item we want to return will be in position size - 1
    */

    private T[] items;
    private int size;

    //Creates an empty list
    public AList(){
        items = (T[]) new Object[100];
        size = 0;
    }

    public void addLast(T x){
        //the next item we want to add will go into position size
        if (size == items.length){
            resize(size * 2); // * 2 to speed up when exceeding size (don't have to resize has as many times) (must be multiplicative, can't add a constant
        }
        items[size] = x;
        size ++;
    }

    public T getLast(){
        return items[size - 1];
    }

    public T get(int i){
        return items[i];
    }

    public int size(){
        return size;
    }

    public T removeLast(){
        T removed = items[size - 1];
        items[size - 1] = null; //not necessary for ints/primitive types but necessary to avoid loitering if storing references
        size --;
        return removed;
    }

    /** Resizes the underlying array to the target capacity */
    private void resize(int capacity){
        //Note this method does not adjust the size variable itself
        T[] new_items = (T[]) new Object[capacity];
        System.arraycopy(items,0,new_items,0,size);
        items = new_items;
    }

    private int usage_ratio(){
        return size / items.length;
    }

}

