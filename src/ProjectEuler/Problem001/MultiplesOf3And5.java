package ProjectEuler.Problem001;

public class MultiplesOf3And5 {
    public static void main(String[] args) {
        System.out.println(getSumOfMultiplesOf3Or5BelowN(1000));
    }

    public static int getSumOfMultiplesOf3Or5BelowN(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
