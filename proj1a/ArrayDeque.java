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

    //Takes constant time unless resizing (no need to manage usage ratio here; usage going up and will necessarily be above 30% post-resize if resizing)
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

    //must takes constant time unless resizing
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
            else{
                int first = nextFirst + 1;
                //accounting for circularity in constant time; checks if we need to loop back around to left side
                if (i + first > items.length - 1){
                    return items[i + first - items.length];
                }
                else{
                    return items[i + first];
                }
            }
    }

    //Takes constant time unless resizing
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        else {
            if(usage_ratio() < 0.3 && size > 8){ //could resize below 8 but would have to alter constructor; probably just change nextFirst to items.length - 1
                resize(size); //no thorough logic behind using size rather than size * 2 here, just figure list will keep trending down so more efficient to cut more early
            }

            //if nextFirst at last spot on right ie first item is at index 0
            if (nextFirst + 1 > items.length - 1) {
                T removed = items[0];
                items[0] = null; //avoids loitering
                nextFirst = 0;
                size--;
                return removed;
            } else {
                T removed = items[nextFirst + 1];
                items[nextFirst + 1] = null; //avoids loitering
                nextFirst++;
                size--;
                return removed;
            }
        }
    }

    //Takes constant time unless resizing
    public T removeLast(){
        if (isEmpty()){
            return null;
        }

        else{
            if(usage_ratio() < 0.3 && size > 8){
                resize(size);
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
        double used = size;
        double capacity = items.length;
        double usage = used / capacity;
        return usage;
    }

    /*
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
    */
}
