import java.util.Stack;
// Wk 5 discussion 1: define a Queue class that implements the push and poll methods of a queue ADT
// using only a Stack class which implements the stack ADT
public class Queue<E> {

    private Stack<E> stack = new Stack<E>();
    private int size;

    public Queue(){
        size = 0;
    }

    public void push (E element){
        Stack<E> temp = new Stack<E>();

        while(!stack.isEmpty()){
            temp.push(stack.pop());
        }
        stack.push(element);

        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }

        size++;
    }

    public E pull(){
        size--;
        return stack.pop();
    }

    public static void main(String[] args) {
        Queue<Integer> test_queue = new Queue<>();
        test_queue.push(2);
        test_queue.push(4);
        test_queue.push(10);
        System.out.println(test_queue.pull());
    }
}
