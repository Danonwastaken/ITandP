package Task3;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, 0));
        System.out.println(checkPerfect(12));
        System.out.println(flipEndChars("Arcade"));
        System.out.println(isValidHexCode("#CDF717"));
        System.out.println(rightTriangle(3,4,5));
        System.out.println(same(new int [] {1, 3, 4, 4, 5}, new int [] {2, 2, 5, 7}));
        System.out.println(isKaprekar(297));
        System.out.println(longestZero("10001100000000101"));
        System.out.println(nextPrime(11));
        System.out.println(findZip("all zip files are zipped zip"));
    }

    public static int solutions(int a, int b, int c) {
        int pow = 2;
        double D = Math.pow(b, pow) - 4 * a * c;
        if (D > 0) {
            return 2;
        }
        else if (D == 0) {
            return 1;
        }

        return 0;
    }
    
    public static boolean checkPerfect(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        if (n == sum) {
            return true;
        }
        return false;
    }

    public static String flipEndChars(String s) {
        char [] charArray = s.toCharArray();
        char firstSymbol = s.charAt(0);
        char lastSymbol = s.charAt(s.length()-1);
        if (firstSymbol == lastSymbol) {
            return "Two's a pair.";        
        }
        else 
        if (s.length() < 2) {
            return "Incompatible.";
        }
        else {
            char newString = charArray[0];
            charArray[0] = charArray[charArray.length - 1];
            charArray[charArray.length - 1] = newString;
            String result = new String(charArray);
            return result;
        }
    }
    public static boolean isValidHexCode(String s) {
        if (!(s.startsWith("#") && s.length() == 7)) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            char symbol = s.charAt(i);
        
        if (symbol > 9 && symbol > 'F') 
            return false;
        }
        return true;
    }
    public static boolean rightTriangle(int a, int b, int c) {
        return (a * a == b * b + c * c || b * b == a * a + c * c || c * c == a * a + b * b);
    }
    public static boolean same(int [] arr1, int [] arr2) {
        int[] newArr1 = Arrays.stream(arr1).distinct().toArray();
        int[] newArr2 = Arrays.stream(arr2).distinct().toArray();
        int Arr1length = newArr1.length;
        int Arr2length = newArr2.length;
        return Arr1length == Arr2length;
    }

    public static boolean isKaprekar(int n) {
        int nPow = n * n;
        String s = Integer.toString(nPow);
        int mid = s.length() / 2;
        String s1, s2;
        if (s.length() < 2) {
            s1 = "0";
        }
        else {
            s1 = s.substring(0, mid);
        }
        s2 = s.substring(mid);
        int s11 = Integer.parseInt(s1);
        int s22 = Integer.parseInt(s2);
        int e = s11 + s22;
        return (e == n);
    }
    public static int longestZero(String s) {
        int tmp = 0;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                tmp += 1;
            }
            else if (tmp != '0') {
                    if  (tmp > cnt) {
                        cnt = tmp;
                    tmp  = 0;    
                    }
            }
            }    
            
        
        return cnt;
    }
    public static int findZip(String s) {
        int cnt = s.split("zip").length - 1;
        if (cnt >= 2) {
            return s.lastIndexOf("zip");
        }
        return -1;
    }

    public static int nextPrime(int n) {
        while(true) {
            int cnt = 0;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    cnt = cnt + 1;
                }
                if (cnt >= 1) {    
                    n = n + 1;
                    break;
                }
            }    
            if (cnt == 0) {
                return n;
            }
            else { 
                return nextPrime(n);
            }        
        }    
    }
}
