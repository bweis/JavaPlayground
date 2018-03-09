package ProjectEuler.Problem002;

public class EvenFibonacciNumbers {
    public static void main(String[] args) {
        int sum = 0, num = 0, index = 0;

        while (num <= 4000000) {
            num = fibDynamic1Space(index++);
            sum += num % 2 == 0 ? num : 0;
        }
        System.out.println(sum);
    }

    private static int fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    private static int fibDynamicNSpace(int n) {
        int f[] = new int[n + 1];

        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++)
            f[i] = f[i-1] + f[i-2];
        return f[n];
    }

    private static int fibDynamic1Space(int n){
        int n1 = 0, n2 = 1;
        int sum;

        if (n == n1 || n == n2) {
            return n;
        }

        for(int i=2; i <= n; i++){
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return n2;
    }

}
