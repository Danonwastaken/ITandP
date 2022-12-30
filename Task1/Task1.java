package Task1;

public class Task1 {
    public static void main(String[] args) {
        System.out.println(remainder(1, 3));
        System.out.println(triArea(3, 2));
        System.out.println(animals(2, 3, 5));
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(operation(24, 15, 9));
        System.out.println(ctoa('['));
        System.out.println(addUpTo(10));
        System.out.println(nextEdge(8, 10));
        System.out.println(sumOfCubes(new int[]{1, 5, 9}));
        System.out.println(abcmath(42,5,10));
    }
    //Task 1/10
    public static int remainder(int a, int b) {
        return (a % b);
    }
    //Task 2/10
    public static int triArea(int a, int h) {
        return ((a * h) / 2);
    }
    //Task 3/10
    public static int animals(int chickens, int cows, int pigs) {
        return (chickens * 2 + cows * 4 + pigs * 4);
    }
    //Task 4/10
    public static boolean profitableGamble(double prob, double prize, double pay) {
            return ((prob * prize - pay) > 0);
        }
    //Task 5/10
    public static String operation(double a, double b, double c) {
        if (a + b == c) {
            return ("Сложить");
        }
        if (a - b == c) {
            return ("Вычесть");
        }
        if (a * b == c) {
            return ("Умножить");
        }
        if (a / b == c) {
            return ("Делить");
        } else {
            return ("Ничего");
        }
    }
    //Task 6/10
    public static int ctoa(char symbol) {
        int ASCII = symbol;
        return (ASCII);
    }
    //Task 7/10
    public static int addUpTo(int a) {
        int res = 0;
        for (int i = 0; i <= a; i++) {
            res = res + i;
        }
        return res;
    }
    //Task 8/10
    public static int nextEdge(int a, int b) {
        return (a + b - 1);
    }
    //Task 9/10
    public static int sumOfCubes(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Math.pow(arr[i], 3);
        }
        return sum;
    }
    //Task 10/10
    public static boolean abcmath(int a, int b, int c) {
        for (int i = 0; i < b; i++) {
            a += a;
            System.out.print(a + " ");
        }
        if (a % c == 0) {
            return true;
        }
        return false;
    }
}