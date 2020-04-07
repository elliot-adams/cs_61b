public class LinkedListDeque<T> {

    public class Node{

        public T item;
        public Node next;
        public Node prev;

        public Node(T t, Node n, Node p){
            item = t;
            next = n;
            prev = p;
        }

        //called by getRecursive in LinkedListDeque class
        private T getRecursive(int index){
            if (index == 0){
                return item;
            }
            return this.next.getRecursive(index - 1);
        }

    }

    private Node sentinel;
    private int size;

    //for empty list scenario
    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel = new Node(null,null,null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }




    public void addFirst(T x){
        //if list was previously empty
        if (sentinel.next == null && sentinel.prev == null){
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.next = new Node(x, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }

    public void addLast(T x){
        //if list was previously empty
        if (sentinel.next == null && sentinel.prev == null){
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.prev = new Node(x, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size++;
    }

    public boolean isEmpty(){
        //could've just returned size == 0
        return (sentinel.next == null || sentinel.next == sentinel) && (sentinel.prev == null || sentinel.prev == sentinel);
    }

    // Must take constant time so cached size
    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space
    public void printDeque(){
        if (size == 0){
            System.out.print("Empty deque ");
        }
        else {
            Node ptr = this.sentinel.next;
            for (int i = 0; i < this.size(); i++) {
                System.out.print(ptr.item + " ");
                ptr = ptr.next;
            }
        }
        System.out.print("(" + size +")"); //just for reference when checking
        System.out.println(""); //to differentiate from whatever will be printed next
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    // Must not involve looping or recursion and must take constant time (ie not size dependent
    public T removeFirst(){
        if (sentinel.next == null && sentinel.prev == null){
            return null;
        }
        Node removed = new Node(sentinel.next.item,null,null);
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return removed.item;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    // Must not involve looping or recursion and must take constant time (ie not size dependent
    public T removeLast(){
        if (sentinel.next == null && sentinel.prev == null){
            return null;
        }
        Node removed = new Node(sentinel.prev.item,null,null);
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return removed.item;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque and must use iteration, not recursion */
    public T get(int index){
        //see if opening up to non ints allows something similar to the below but with null instead of 0
        if (index > size -1 || index < 0) {
            return null;
        }
        Node ptr = this.sentinel.next;
        for (int i = 0; i < index; i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    //Same as get above but recursive
    public T getRecursive(int index){
        if (index > size -1 || index < 0) {
            return null;
        }
        else {
            Node ptr = this.sentinel.next;
            return ptr.getRecursive(index);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> N = new LinkedListDeque<>();
        System.out.println(N.isEmpty());
        N.addFirst(5);
        N.printDeque();
        N.addLast(34);
        N.printDeque();
        N.removeLast();
        N.printDeque();
        N.removeFirst();
        N.printDeque();
        System.out.println(N.isEmpty());
        N.addFirst(12);
        N.addLast(17);
        N.printDeque();
        System.out.println();

        LinkedListDeque<Integer> L = new LinkedListDeque<>(15);
        System.out.println(L.isEmpty());
        L.printDeque();
        L.addFirst(10);
        L.printDeque();
        L.addFirst(5);
        L.printDeque();
        L.addLast(34);
        L.printDeque();
        L.addLast(17);
        L.printDeque();
        System.out.println();

        System.out.println(L.removeFirst());
        L.printDeque();
        System.out.println(L.removeLast());
        L.printDeque();
        L.removeLast();
        L.removeLast();
        L.printDeque();
        L.removeLast();
        L.printDeque();
        System.out.println(L.isEmpty());
        L.addLast(34);
        L.addFirst(10);
        L.printDeque();
        L.addLast(12);
        L.printDeque();
        System.out.println(L.get(0));
        System.out.println(L.getRecursive(0));
        System.out.println(L.get(1));
        System.out.println(L.getRecursive(1));
        System.out.println(L.get(2));
        System.out.println(L.getRecursive(2));
        System.out.println(L.get(3));
        System.out.println(L.getRecursive(3));
        System.out.println();

        LinkedListDeque<Integer> M = new LinkedListDeque<>();
        M.printDeque();
        System.out.println(M.isEmpty());
        M.addFirst(10);
        M.printDeque();
        System.out.println(M.isEmpty());
        M.removeLast();
        M.printDeque();
        System.out.println(M.isEmpty());
        M.addLast(17);
        M.printDeque();
        System.out.println(M.get(0));
        System.out.println(M.getRecursive(0));
        System.out.println(M.get(1));
        System.out.println(M.getRecursive(1));
        M.printDeque();
        System.out.println(M.isEmpty());
        M.removeFirst();
        M.printDeque();
        System.out.println(M.isEmpty());
        System.out.println(M.get(8));
        System.out.println(M.getRecursive(8));


    }


}
