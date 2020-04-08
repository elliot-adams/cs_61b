/*
    Explicitly test:
    addFirst
    addLast
    removeFirst
    removeLast
    printDeque

    Implicitly test:
    isEmpty
    size
    get

    Next round need to test:
    Resizing
    Usage ratio management
    may want a quick print usage ratio method
 */

public class ArrayDequeTest {

    public static void main(String[] args) {
        /*
        //Basic tests
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.printDeque();

        //Sequence should always be 3, 4, 9, 12, 15, 17, 19, 34 or a subset thereof
        //addFirst
        A.addFirst(34);
        A.printDeque();
        A.addFirst(19);
        A.printDeque();
        A.addFirst(17);
        A.printDeque();
        A.addFirst(15);
        A.printDeque();
        A.addFirst(12);
        A.printDeque();
        A.addFirst(9);
        A.printDeque();
        A.addFirst(4);
        A.printDeque();
        A.addFirst(3);
        A.printDeque();
        System.out.println();

        //removeFirst
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        A.removeFirst();
        A.printDeque();
        System.out.println();

        //addLast
        A.addLast(3);
        A.printDeque();
        A.addLast(4);
        A.printDeque();
        A.addLast(9);
        A.printDeque();
        A.addLast(12);
        A.printDeque();
        A.addLast(15);
        A.printDeque();
        A.addLast(17);
        A.printDeque();
        A.addLast(19);
        A.printDeque();
        A.addLast(34);
        A.printDeque();
        System.out.println();

        //removeLast
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        A.removeLast();
        A.printDeque();
        System.out.println();
         */

        //scenario where we call removeLast before ever having added last so have to jump to other end,
        //and then addLast so have to jump back to opposite end
        ArrayDeque<Integer> B = new ArrayDeque<>();
        B.addFirst(34);
        B.printDeque();
        System.out.println(B.removeLast());
        B.printDeque();
        B.addLast(34);
        B.printDeque();
        B.addLast(17);
        B.printDeque();
        B.addFirst(12);
        B.printDeque();
        B.removeFirst();
        B.printDeque();
        B.removeLast();
        B.printDeque();
        B.removeLast();
        B.printDeque();
        System.out.println();

        //scenario where we call removeFirst before ever having added first so have to jump to other end,
        //and then addFirst so have to jump back to opposite end
        ArrayDeque<Integer> C = new ArrayDeque<>();
        C.addLast(34);
        C.printDeque();
        System.out.println(C.removeFirst());
        B.printDeque();
        B.addFirst(34);
        B.printDeque();
        B.addFirst(17);
        B.printDeque();
        B.addLast(12);
        B.printDeque();
        B.removeLast();
        B.printDeque();
        B.removeFirst();
        B.printDeque();
        B.removeFirst();
        B.printDeque();

    }
}
