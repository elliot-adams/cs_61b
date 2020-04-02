public class whileSum {
    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 10; i += 1) {
            int sum = 0;
            for (int k = 0; k <= i + 1; k += 1) {
            	sum += k;
            }
            System.out.print(sum + " ");
        }
    }
}