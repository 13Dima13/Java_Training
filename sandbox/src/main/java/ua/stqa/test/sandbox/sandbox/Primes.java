package ua.stqa.test.sandbox.sandbox;


public class Primes {
  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i++) { //Цикл (+= вместо i+1б, ++ - инкрементирование)
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }


  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i<n && n % i != 0) { 
      i++;
    }
    return i == n;
  }
}