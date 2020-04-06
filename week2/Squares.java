public class Squares{

	public static intList square(intList L){

		if (L.rest == null){
			return new intList(L.first * L.first, null);
		}

		return new intList(L.first * L.first, square(L.rest));
	}

	public static intList square_ans(intList L) {

		if (L == null){
			return L;
		}

		intList rest = square_ans(L.rest);
		intList M = new intList (L.first * L.first, rest);
		return M;

	}

	public static intList square_mutative(intList L){
		//mutative meaning it changes the values of the list you pass
		intList B = L;

		while (B != null){
			B.first *= B.first;
			B = B.rest;
		}

		return L;
	}

	public static void main(String[] args) {
		
		intList test = new intList(10, null);
		test = new intList(5,test);
		test = new intList(34,test);
		intList squared_list = square(test);

		System.out.println(squared_list.get(0));
		System.out.println(squared_list.get(1));
		System.out.println(squared_list.get(2));
	}
}