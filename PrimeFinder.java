import java.util.ArrayList;
import java.util.List;

public class PrimeFinder {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide an upper limit X as a command-line argument.");
            return;
        }

        int upperLimit;
        try {
            upperLimit = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please provide a valid integer.");
            return;
        }

        List<Integer> primes = findPrimes(upperLimit);
        if (!primes.isEmpty()) {
            System.out.println("Total number of primes under " + upperLimit + ": " + primes.size());
            System.out.println("Largest prime number under " + upperLimit + ": " + primes.get(primes.size() - 1));
        } else {
            System.out.println("No primes found under " + upperLimit);
        }
    }

    public static List<Integer> findPrimes(int upperLimit) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[upperLimit];

        for (int i = 2; i < upperLimit; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < upperLimit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < upperLimit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < upperLimit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
