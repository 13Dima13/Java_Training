package ua.stqa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.sandbox.sandbox.Primes;


public class PrimeTests {
  @Test
  public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }


  @Test (enabled = false)
  public void testPrimeLong() { //действия с длинными числами, всегда длинее
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
}
