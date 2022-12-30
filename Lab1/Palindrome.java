package Lab1;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println("Да, это палиндром");
            }
            else {
                 System.out.println("Нет, это не палиндром");
            }
        } 
    }
    //Метод, изменяющий символы в строке
    public static String reverseString(String s) {
        String reverse = "";
        for (int i = 0; i < s.length(); i++) {
            reverse = reverse + s.charAt(s.length() - 1 - i);
        }
        return reverse;
    //Метод, сравнивающий первоначальное слово с перевёрнутым
    }
    public static boolean isPalindrome(String s) {
        if (s.equals(reverseString(s)) ) {
            return true;
        }
        return false;
    }

  
}

