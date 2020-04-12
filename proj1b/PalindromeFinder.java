/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        Palindrome palindrome = new Palindrome();
        OffByN offbyn;

        for (int i = 0; i < 4; i++) {
            offbyn = new OffByN(i);
            In in = new In("../library-sp18/data/words.txt");
            int count = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offbyn)) {
                    System.out.println(word);
                    count++;
                }
            }
            System.out.println(count + "\n\n\n");
        }
    }
}
