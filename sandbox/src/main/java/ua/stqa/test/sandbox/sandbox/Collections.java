package ua.stqa.test.sandbox.sandbox;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List <String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (int i = 0; i < languages.size(); i++) {
      System.out.println("I want to learn " + languages.get(i));
    }
  }
}
