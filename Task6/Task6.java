package Task6;

import java.util.ArrayList;


public class Task6 {
    public static void main(String[] args) {
        System.out.println(bell(5));
        System.out.println(translateWord("shrimp"));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(convertToRoman(156));
        System.out.println(formula("16 * 10 = 160"));
        System.out.println(palindromedescedant(23336014));
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(ulam(9));
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));


    }
    public static int stirling(int k, int j){
        if (k == 0 && j == 0) {
            return 1;
        }
        if (k == 0) {
            return 0;
        }
        if (k > j) {
            return 0;
        }
        else {
            return stirling(k - 1, j - 1) + k * stirling(k, j - 1);
        }
    }
    public static int bell(int n) {
        int res = 0;
        for (int i = 0; i <= n; i++){
            res += stirling(i, n);
        }
        return res;
    }


    public static String translateWord(String s) {
        String lets = "aeiouyAEIOUY";
        int n = 0;
        if (lets.indexOf(s.charAt(0)) != -1)
            return s + "yay";
        else {
            while (lets.indexOf(s.charAt(n)) == -1) {
                n++;
            }
            return s.substring(n) + s.substring(0, n) + "ay";
        }
    }

    public static String translateSentence(String sentence) {
        String splittedString = sentence.replace(".", "");
        String[] word = splittedString.split(" ");
        for (int i = 0; i < word.length; i++) {
            word[i] = translateWord(word[i]);
        }
        return String.join(" ", word);
    }
    public static boolean validColor(String s){
        if(!s.contains("rgb")) {
            return false;
        }
        Boolean flag = false;
        if(s.contains("rgba")){
            flag = true;
            s = s.substring(4);
        } else {
            s = s.substring(3);
        }
        if(s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
            return false;
        }
        s = s.substring(1, s.length() - 1);
        String[] nums = s.split(",");

        if(!flag && nums.length != 3) {
            return false;
        }

        if (nums.length == 3) {
            for (int i = 0; i < 3; i++) {
                try {
                    int num = Integer.parseInt(nums[i]);
                    if (num < 0 || num > 255) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        if(nums.length == 4){
            try{
                double num = Double.parseDouble(nums[3]);
                if(num < 0 || num > 1) {
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }
    public static String convertToRoman(int n) {
        if (n < 0 || n > 3999) {
            return ("Число выходит из диапазона 0 - 3999");
        }
        if (n == 0) {
            return ("N");
        }
        String symbols[] = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int nums[] = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < symbols.length; i++) {
            while (n >= nums[i]) {
                n -= nums[i];
                res.append(symbols[i]);
            }
        }
        return res.toString();
    }

    public static boolean formula(String form) {
        int cnt = form.split("=").length - 1;
        if (cnt >= 2) {
            return false;
        }
        else {
            String[] operations = form.split(" [-+*/=] ");
            int a = Integer.parseInt(operations[0]);
            int b = Integer.parseInt(operations[1]);
            int c = Integer.parseInt(operations[2]);
            if (a + b == c) {
                return true;
            }
            if (a - b == c) {
                return true;
            }
            if (a * b == c) {
                return true;
            }
            if (a / b == c) {
                return true;
            }
        }
        return false;
    }
    public static boolean reverseString(String m) {
        String reverse = "";
        for (int i = 0; i < m.length(); i++) {
            reverse += m.charAt(m.length() - 1 - i);
        }
        if (Integer.parseInt(m) != Integer.parseInt(reverse)) {
            return false;
        }
        return true;
    }

    public static boolean palindromedescedant(int num) {
        String s = String.valueOf(num);
        StringBuilder str = new StringBuilder();
        if (s.length() % 2 != 0) {
            return false;
        }
        while (s.length() > 1) {
            if (reverseString(s)) {
                return true;
            }
            for (int i = 0; i < s.length() / 2; i ++) {
                int left = Character.getNumericValue(s.charAt(i * 2));
                int right = Character.getNumericValue(s.charAt(i * 2 + 1));
                str.append(left + right);
            }
            s = str.toString();
        }
        return false;
    }

    public static String longestNonrepeatingSubstring(String s) {
        String str = "";
        String subStrA = "";
        String subStrB = "";
        for (int i = 0; i < s.length(); i++) {
            if (str.contains(s.charAt(i) + "")) {
                if (subStrB.length() > subStrA.length()) {
                    subStrA = subStrB;
                }
                subStrB = "";
            }
            else {
                str += s.charAt(i) + "";
                subStrB += s.charAt(i) + "";
                if (subStrB.length() > subStrA.length()) {
                    subStrA = subStrB;
                }
            }
        }
        return subStrA;
    }
    public static int ulam(int n){
        int[] uElement = new int[n];
        uElement[0] = 1;
        uElement[1] = 2;
        int uElementIndex = 2;
        int ulamNumber = 3;
        while(uElementIndex < n){
            int cnt = 0;
            for(int i = 0; i < uElementIndex; i++){
                for(int j = i + 1; j < uElementIndex; j++){
                    if(uElement[i] + uElement[j] == ulamNumber)
                        cnt++;
                }
            }
            if(cnt == 1){
                uElement[uElementIndex] = ulamNumber;
                uElementIndex++;
            }

            ulamNumber++;
        }
        if(n < 1) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        return uElement[n - 1];
    }

    public static String getHashTags(String s) {
        ArrayList<String> hashTags = new ArrayList<>();
        String [] k = s.toLowerCase().replace(",", "").split(" ");
        for (int i = 0; i < k.length - 1; i++) {
            for (int j = i + 1; j < k.length; j++) {
                if (k[i].length() > k[j].length()) {
                    String temp = k[i];
                    k[i] = k[j];
                    k[j] = temp;
                }
            }
        }
        for (int l = 1; l <= 3; l++) {
            hashTags.add('#' + k[k.length - l]);
        }
        return hashTags.toString();
    }
    public static String stripUrlParams(String url) {
        return stripUrlParams(url, null);
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        StringBuilder buidler = new StringBuilder();
        if (!url.contains("?")) {
            return url;
        }
        buidler.append(url, 0, url.indexOf("?") + 1);
        String[] paramsSource = url.substring(url.indexOf("?") + 1).split("&");
        ArrayList<String[]> pairs = new ArrayList<>();
        for (String param : paramsSource) {
            String[] keyValue = param.split("=");
            String[] pair = new String[]{keyValue[0], keyValue[1]};
            boolean flag = false;

            if (paramsToStrip != null) {
                for (int i = 0; i < paramsToStrip.length; i++) {
                    if (pair[0].equals(paramsToStrip[i])) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                for (int i = 0; i < pairs.size(); i++) {
                    String[] pair2 = pairs.get(i);
                    if (pair2[0].equals(pair[0])) {
                        pairs.set(i, pair);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                pairs.add(pair);
            }
        }
        for (String[] pair : pairs) {
            buidler.append(String.format("&%s=%s", pair[0], pair[1]));
        }
        return buidler.toString();
    }
}

