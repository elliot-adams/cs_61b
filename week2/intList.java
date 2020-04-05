//We need lists because arrays have a finite size; 
//need to be able to add to etc.

public class intList{

	public int first;
	public intList rest;

	public intList(int f, intList r){
		first = f;
		rest = r;
	}

	// Recursive size method
	public int size(){
		// Base case
		if (this.rest == null){
			return 1;
		}
		return 1 + this.rest.size();
	}

	// Iterative size method
	public int iterativeSize(){
		intList p = this;
		int size = 1;
		
		while(p.rest!=null){
			p = p.rest;
			size++;
		}

		return size;
	}

	// Get ith item iterative
	public int get(int i){
		if (i == 0){
			return this.first;
		}
		return this.rest.get(i-1);
	}

	// Get ith item iterative
	public int iterativeGet(int i){
		intList p = this;
		int k = 0;
		while(k<i){
			p = p.rest;
			k++;
		}
		return p.first;
	}

	public static void main(String[] args) {

		intList L = new intList(15,null);
		L = new intList(10,L);
		L = new intList(5,L);

		System.out.println(L.size());
		System.out.println(L.iterativeSize());
		
		System.out.println(L.get(1));
		System.out.println(L.iterativeGet(1));
		
	}

}