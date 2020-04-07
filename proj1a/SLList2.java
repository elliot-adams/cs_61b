public class SLList2{

	//Nesting IntNode class since it's basically a subordinate class
	//If the nested class has no need to use any of the instance methods 
	//or variables of SLList, you may declare the nested class static 
	//Declaring a nested class as static means that methods inside 
	//the static class can not access any of the members of the enclosing class
	//ie first, addFirst, getFirst etc. This saves a bit of memory
	public static class IntNode{

		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n){
			item = i;
			next = n;
		}

	}

	//Private variables and methods can only be 
	//accessed by code inside the same .java file
	//use private here so a user can't create a malformed list
	//Conversely, "When you create a public member (i.e. method or variable), 
	//be careful, because you're effectively committing to supporting 
	//that member's behavior exactly as it is now, forever"
	
	//now with sentinel, fist item of the list (if not empty) is at sentinel.next
	private IntNode sentinel;
	private int size;

	//need to allow for empty list scenario
	public SLList2(){
		//34 is arbitrary; represents '??' in diagrams
		sentinel = new IntNode(34,null);
		size = 0;
	}

	public SLList2(int x){
		sentinel = new IntNode(34,null);
		sentinel.next = new IntNode(x,null);
		size = 1;
	}

	public void addFirst(int x){
		sentinel.next = new IntNode(x,sentinel.next);
		size++;
	}

	public int getFirst(){
		return sentinel.next.item;
	}

	public int size(){
		return size;
	}

	public void addLast(int x){
		IntNode p = sentinel;
		while(p.next != null){
			p = p.next;
		}

		p.next = new IntNode(x,null);
		size++;
	}

	public static void main(String[] args) {
		SLList2 L = new SLList2(15);
		L.addFirst(10);
		L.addFirst(5);
		int f = L.getFirst();
		System.out.println(f);
		System.out.println(L.size());
		L.addLast(20);
		System.out.println(L.size());
	}
}