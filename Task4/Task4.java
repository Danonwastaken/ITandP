package Task4;

import java.util.ArrayList;

public class Task4 {
    public static void main(String[] args) {
        System.out.println(toCamelCase("С_ha_nge"));
        System.out.println(toSnakeCase("helloWorld"));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(bugger(0));
        System.out.println(bessysEssay(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(trouble(1222345, 12345));
        System.out.println(toStarShorthand("abbccc"));
    }

    public static String toCamelCase(String a) {
        String[] words = a.split("_");
        String sb = words[0];
        for (int i = 1; i < words.length; i++) {
            sb += ((words[i]).substring(0, 1)).toUpperCase() + (words[i]).substring(1);
        }
        return sb;
    }

    public static String toSnakeCase(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') {
                int endIndex = a.indexOf(a.charAt(i));
                return (a.substring(0, endIndex) + "_" + (a.substring(endIndex)).toLowerCase());
            }
        }
        return a;
    }

    public static String overTime(double arr[]) {
        double startDay = arr[0];
        double endDay = arr[1];
        double hourlyRate = arr[2];
        double extra = arr[3];
        if (endDay <= 17) {
            return "$" + ((endDay - startDay) * hourlyRate);
        } else {
            return "$" + ((17 - startDay) * hourlyRate + (endDay - 17) * hourlyRate * extra);
        }
    }

    public static String BMI(String weight, String height) {
        double w = Double.parseDouble(weight.split(" ")[0]);
        String wType = weight.split(" ")[1];
        double h = Double.parseDouble(height.split(" ")[0]);
        String hType = height.split(" ")[1];
        if (wType.equals("pounds")) {
            w = w * 0.453592;
        }
        if (hType.equals("inches")) {
            h = h * 0.0254;
        }
        double imt = (w / (Math.pow(h, 2)));
        String s = String.format("%.1f", imt);
        if (imt < 18.5) {
            return (s + " " + "Underweight");
        }
        if (imt >= 25) {
            return (s + " " + "Overweight");
        } else {
            return (s + " " + "Normal weight");
        }
    }

    public static int bugger(int a) {
        int cnt = 0;
        while (a > 9) {
            int tmp = 1;
            while (a > 0) {
                tmp *= a % 10;
                a /= 10;
            }
            a = tmp;
            cnt++;
        }
        return cnt;
    }

    public static String bessysEssay(int n, int k, String essay) {
        String finalEssay = "";
        String[] arr = essay.split(" ");
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].length() + cnt <= k) {
                cnt += arr[i].length();
                finalEssay += arr[i] + " ";
            } else {
                finalEssay += "\n" + arr[i] + " ";
                cnt = arr[i].length();
            }
        }
        return finalEssay;
    }

    public static int countUniqueBooks(String a, char symbol) {
        int cnt = 1;
        String b = "";
        String s = Character.toString(symbol);
        String arr[] = a.split(s);
        for (int i = 1; i < arr.length; i += 2) {
            b += arr[i];
        }
        if (b.length() == 0) {
            cnt = 0;
        }
        for (int i = 1; i < b.length(); i++) {
            if (b.charAt(i - 1) != b.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static String split(String hooks) {
        int leftSide = 0;
        int rightSide = 0;
        String newHooks = "";
        ArrayList<String> pairs = new ArrayList<>();
        for (int i = 0; i < hooks.length(); i++) {
            if (hooks.charAt(i) == '(') {
                newHooks += "(";
                leftSide++;
            }
            if (hooks.charAt(i) == ')') {
                newHooks += ")";
                rightSide++;
            }
            if (leftSide == rightSide) {
                leftSide = rightSide = 0;
                pairs.add(newHooks);
                newHooks = "";
            }
        }
        return pairs.toString();
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

    public static boolean trouble(long a, long b) {
        String number1 = String.valueOf(a);
        String number2 = String.valueOf(b);
        for (int i = 2; i < number1.length(); i++) {
            for (int j = 1; j < number2.length(); j++) {
                if (number1.charAt(i - 2) == number1.charAt(i - 1) && number1.charAt(i - 1) == number1.charAt(i)) {
                    if (number2.charAt(j - 1) == number2.charAt(j) && number2.charAt(j) == number1.charAt(i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static public String toStarShorthand(String a) {
        int cnt = 1;
        char symbol = a.charAt(0);
        String str = "";

        for (int i = 0; i < a.length(); i++) {
            if (i == 0) {
                symbol = a.charAt(0);
                continue;
            }

            if (a.charAt(i) == (symbol))
                cnt += 1;
            else {
                if (cnt == 1) {
                    str += symbol;
                } else
                    str += symbol + "*" + cnt;

                cnt = 1;
                symbol = a.charAt(i);
            }
        }
            if (cnt == 1)
                str += symbol;
            else
                str += symbol + "*" + cnt;

            return str;
        }

}
