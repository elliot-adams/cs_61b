public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque  = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque d) {
        if (d.size() < 2) {
            return true;
        } else {
            if (d.removeFirst() == d.removeLast()) {
                return isPalindrome(d);
            }
            return false;
        }
    }

    //Overloading
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    private boolean isPalindrome(Deque d, CharacterComparator cc) {
        if (d.size() < 2) {
            return true;
        } else {
            char first = (char) d.removeFirst();
            char last = (char) d.removeLast();
            if (cc.equalChars(first, last)) {
                return isPalindrome(d, cc);
            }
            return false;
        }
    }
}
