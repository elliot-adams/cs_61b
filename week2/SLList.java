//Contains everything up until implementing null scenario and sentinel etc.
public class SLList{

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
	private IntNode first;

	public SLList(int x){
		first = new IntNode(x,null);
	}

	public void addFirst(int x){
		first = new IntNode(x,first);
	}

	public int getFirst(){
		return first.item;
	}

	private static int size(IntNode p){
		if(p.next == null){
			return 1;
		}
		return 1 + size(p.next);
	}

	//allowed to have two methods with diff signatures in java (diff parameters here)
	//this is called overloading
	//alternatively, could cache size to speed up retrieval by declaring
	//'private int size;' as attribute, setting size = 1 in constructor, and incrementing
	//size in addFirst and addLast; makes these marginally slower but makes getting size
	//almost instantaneous ('no such thing as a free lunch')
	public int size(){
		return size(first);
	}

	public void addLast(int x){
		IntNode p = first;
		while(p.next != null){
			p = p.next;
		}

		p.next = new IntNode(x,null);
	}

	public static void main(String[] args) {
		SLList L = new SLList(15);
		L.addFirst(10);
		L.addFirst(5);
		int f = L.getFirst();
		System.out.println(f);
		System.out.println(L.size());
		L.addLast(20);
		System.out.println(L.size());
	}
}