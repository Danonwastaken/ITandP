package Task5;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Task5 {
    public static void main (String[] args) throws NoSuchAlgorithmException {
        System.out.println(numToEng(5));
        System.out.println(numToRus(123));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(Arrays.toString(encrypt("Hello")));
        int[] data = { 72, 33, -73, 84, -12, -3, 13, -13, -68 };
        System.out.println(decrypt(data));
        System.out.println(sumDigProd(16, 28));
        System.out.println(validateCard("1234567890123452"));
        System.out.println(getSha256Hash("password123"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(hexLattice(7));
    }
    public static String numToEng(int a) {
        StringBuilder numEng = new StringBuilder();
        String [] unit = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String [] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String [] teen = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                            "eighteen", "nineteen"};
        String hundred = "hundred";
        if (a < 0 || a > 999) {
            return ("Ошибка! Число выходит из диапазона 0-999");
        }
        if (a > 99) {
            numEng.append(unit[a / 100]).append(" ").append(hundred).append(" ").toString();
            a %= 100;
        }
        if (a > 19) {
            numEng.append(tens[a / 10 - 2]).append(" ");
            a %= 10;
            numEng.append(unit[a]);
        } else {
            if (a > 9) {
                numEng.append(teen[a - 10]);
            } else {
                numEng.append(unit[a]);
            }
        }
        return numEng.toString();
    }
    public static String numToRus(int a) {
        StringBuilder numRus = new StringBuilder();
        String[] unit = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] tens = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят",
                "девяносто"};
        String[] teen = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
                "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        String[] hundreds = {"сто", "двести", "триста", "четыреста", "пятьсот", "семьсот", "восемьсот", "девятьсот"};
        if (a < 0 || a > 999) {
            return ("Ошибка! Число выходит из диапазона 0-999");
        }
        if (a > 99) {
            numRus.append(hundreds[a / 100 - 1]).append(" ");
            a %= 100;
        }
        if (a > 19) {
            numRus.append(tens[a / 10 - 2]).append(" ");
            a %= 10;
            numRus.append(unit[a]);
        } else {
            if (a > 9) {
                numRus.append(teen[a - 10]);
            } else {
                numRus.append(unit[a]);
            }
        }
        return numRus.toString();
    }
    public static boolean canComplete(String letter, String word) {
        int startIdx = 0;
        for (int i = 0; i < letter.length(); i++) {
            int idx = word.indexOf(letter.charAt(i), startIdx);
            if (idx == -1) return false;
            startIdx = idx + 1;
        }
        return true;
    }

    public static int[] encrypt(String s) {
        int[] enc = new int[s.length()];
        enc[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            enc[i] = s.charAt(i) - s.charAt(i-1);
        }
        return enc;
    }

    public static String decrypt(int[] s) {
        String dec = "" + (char) s[0];
        char prevous = (char) s[0];
        for (int i = 1; i < s.length; i++) {
            prevous += s[i];
            dec += prevous;
        }
        return dec;
    }
    public static int sumDigProd(int... n) {
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum += n[i];
        }
        while (sum > 9) {
            int tmp = 1;
            while (sum > 0) {
                tmp *= sum % 10;
                sum /= 10;
            }
            sum = tmp;
        }
        return sum;
    }
    public static boolean validateCard(String card){
        int sum = 0;
        if (card.length() < 14 || card.length() > 19) {
            return false;
        }
        int last = Integer.parseInt(card.charAt(card.length() - 1) + "");//последняя/контрольная цифра
        card = new StringBuilder(card).reverse().toString();
        for (int i = 1; i < card.length(); i++) {
            int digit = Integer.parseInt(card.charAt(i) + "");
            if (i % 2 == 1) {
                sum += digit;
            }
            else {
                digit *= 2;
                if (digit >= 10) {
                    sum += digit / 10 + digit % 10;
                } else {
                    sum += digit;
                }
            }
        }
        return (10 - sum % 10) == last;
    }
    public static String getSha256Hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte a : digest) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
    public static String correctTitle(String s) {
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (words[i].equals("and") || words[i].equals("the") || words[i].equals("of") || words[i].equals("in")) {
                words[i] = word;
            }
            else {
                words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }
        return String.join(" ", words);
    }
    public static boolean canMove(String s, String sCord, String eCord) {
        int startA = sCord.charAt(0);
        int startB = sCord.charAt(1);
        int endA = eCord.charAt(0);
        int endB = eCord.charAt(1);
        if (s.contains("Pawn")) {
            return ((startA == endA) && Math.abs(endB - startB) <= 2);
        }
        if (s.contains("Knight")) {
           return (Math.abs(startA - endA) == 1 && Math.abs(startB - endB) == 2 ||
                   Math.abs(startA - endA) == 2 && Math.abs(startB - endB) == 1);
        }
        if (s.contains("Bishop")) {
            return (Math.abs(startB - endB) == Math.abs(startA - endA));
        }
        if (s.contains("Rook")) {
            return ((startA == endA) || (startB == endB));
        }
        if (s.contains("Queen")) {
            return ((Math.abs(startB - endB) == Math.abs(startA - endA)) ||
                   (Math.abs(startA - endA) == 1 && Math.abs(startB - endB) == 1));
        }
        if (s.contains("King")) {
            return (Math.abs(startA - endA) <= 1 && Math.abs(startB - endB) <= 1);
        }
        return false;
    }
    public static boolean doesRhyme(String a, String b) {
        String arr1[] = a.toLowerCase().split(" ");
        String arr2[] = b.toLowerCase().split(" ");
        String symbols[] = new String[] {"a", "e", "i", "o", "u"};
        for (int i = 0; i < symbols.length; i++) {
            if (arr1[arr1.length - 1].contains((symbols[i]))) {
                if (!arr2[arr2.length - 1].contains(symbols[i]))
                    return false;
            } else if (!arr1[arr1.length - 1].contains(symbols[i])) {
                if (arr2[arr2.length - 1].contains(symbols[i]))
                    return false;
            }
        }
        return true;
    }


    public static String[] sameVowelGroup(String[] strings) {
        List<String> answ = new ArrayList<>();

        for ( int i = 0; i < strings.length; i++ ) {
            if ( doesRhyme(strings[0], strings[i]) ) {
                answ.add(strings[i]);
            }
        }

        return answ.toArray(new String[answ.size()]);
    }
    public static String hexLattice(int num) {

        int d = 1 + 4 * (num - 1) / 3;
        double n1 = (-1 + Math.sqrt(d)) / 2;
        double n2 = (-1 - Math.sqrt(d)) / 2;
        int n = -1;
        if (n1 > 0 && n1 % 1f == 0)
            n = (int) n1;
        else if (n2 > 0 && n2 % 1f == 0) {
            n = (int) n2;
        }
        int linesCount = n * 2 + 1;
        if (n == -1)
            return "invalid";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < linesCount; i++) {
            int dots = 0;
            if (i == n) {
                dots = linesCount;
            } else {
                dots = linesCount - Math.abs(n - i);
            }
            int spacesCount = (linesCount - dots);
            for (int j = 0; j < spacesCount; j++)
                sb.append(" ");
            for (int j = 0; j < dots; j++)
                sb.append("o ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
