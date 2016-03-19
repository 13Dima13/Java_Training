package ua.stqa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.sandbox.sandbox.Primes;


public class PrimeTests {
  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
}
