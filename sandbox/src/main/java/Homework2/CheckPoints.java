package Homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckPoints {

  @Test
  public void TestDistance1(){
    Point p1 = new Point(0, 2);
    Point p2 = new Point(2, 2);
    double x = p2.x - p1.x;
    double y = p2.y - p1.y;
    Assert.assertEquals(Math.sqrt(Math.pow (x, 2) + Math.pow (y , 2)), 2.0);
  }
}