import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PrimeFinderTest {

    @Test
    public void testFindPrimesUnder10() {
        List<Integer> primes = PrimeFinder.findPrimes(10);
        assertEquals(4, primes.size(), "Total number of primes under 10 should be 4");
        assertEquals(7, primes.get(primes.size() - 1), "Largest prime under 10 should be 7");
    }

    @Test
    public void testFindPrimesUnder20() {
        List<Integer> primes = PrimeFinder.findPrimes(20);
        assertEquals(8, primes.size(), "Total number of primes under 20 should be 8");
        assertEquals(19, primes.get(primes.size() - 1), "Largest prime under 20 should be 19");
    }

    @Test
    public void testFindPrimesUnder2() {
        List<Integer> primes = PrimeFinder.findPrimes(2);
        assertEquals(0, primes.size(), "There should be no primes under 2");
    }

    @Test
    public void testFindPrimesWithNonPrimeUpperLimit() {
        List<Integer> primes = PrimeFinder.findPrimes(15);
        assertEquals(6, primes.size(), "Total number of primes under 15 should be 6");
        assertEquals(13, primes.get(primes.size() - 1), "Largest prime under 15 should be 13");
    }

    @Test
    public void testFindPrimesWithLargeLimit() {
        List<Integer> primes = PrimeFinder.findPrimes(100);
        assertEquals(25, primes.size(), "Total number of primes under 100 should be 25");
        assertEquals(97, primes.get(primes.size() - 1), "Largest prime under 100 should be 97");
    }
}
