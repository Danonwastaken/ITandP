package Task2;


public class Task2 {
    public static void main(String[] args) {
        System.out.println(repeat("Mice", 5));
        System.out.println(differenceMaxMin(new int[]{1, 5, 9, 12}));
        System.out.println(isAvgWhole(new int[]{1, 5, 9, 12}));
        System.out.println(getDecimalPlaces("51416.314"));
        int[] sumCumulative = cumulativeSum(new int[]{1, 5, 9, 12});
        for (int i : sumCumulative) {
            System.out.print(i + " ");
        }
        System.out.println(isValid("51436"));
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(Fibonacci(7));
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println(boxSeq(5));
    }

    public static String repeat(String source, int count) {
        String answer = "";
        for (int i = 0; i < source.length(); i++) {
            for (int j = 0; j < count; j++) {
                answer += source.charAt(i);
            }
        }
        return answer;
    }

    public static int differenceMaxMin(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        int difference = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            difference = max - min;
        }
        return difference;
    }

    public static boolean isAvgWhole(int[] arr1) {
        double sum = 0;
        double average = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum += arr1[i];
        }
        average = sum / arr1.length;
        if (average % 1 == 0) {
            return true;
        }
        return false;
    }

    public static int[] cumulativeSum(int[] arr2) {
        int[] answer;
        answer = new int[arr2.length];
        int sum = 0;
        for (int i = 0; i < arr2.length; i++) {
            answer[i] = arr2[i] + sum;
            sum += arr2[i];
        }
        return answer;
    }

    public static int getDecimalPlaces(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                return number.length() - i - 1;
            }
        }
        return 0;
    }
    public static boolean isValid(String number) {
        if (number.length() > 5 || number.indexOf(" ") != -1) {
            return false;
        }
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isStrangePair(String firstString, String secondString) {
        return (firstString.charAt(0) == secondString.charAt(secondString.length() - 1)
                && secondString.charAt(0) == firstString.charAt(firstString.length() - 1));
    }

    public static int Fibonacci(int number) {
        number = number + 1;
        int r = (int) ((Math.pow(((1+Math.sqrt(5))/2), number) - Math.pow(((1-Math.sqrt(5))/2), number)) / Math.sqrt(5));
        return r;
    }

    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(prefix.replace("-", ""));
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.endsWith(suffix.replace("-", ""));
    }

    public static int boxSeq(int number) {
        int res = 0;
        for (int i = 1; i<= number; i++) {
            if (i % 2 != 0) {
                res = res + 3;
            }
            else {
                res = res - 1;
            }
        }
        return res;
    }
}


