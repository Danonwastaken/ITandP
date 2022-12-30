package Lab1;

public class Primes {
    //Перебор чисел в диапазоне от 2 до 100 включительно
    public static void main(String[] args) {
        for(int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }    
    }
    //Определение аргумента простым числом, или нет
    public static boolean isPrime(int n) {
        for(int i = 2; i < n; i++) {
            if(n % i ==0) {
                return false;
            }
    }
        return true;
    }
}
