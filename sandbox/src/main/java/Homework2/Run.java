package Homework2;

public class Run {
  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 1);

    System.out.println("Distance between p2 and p1: p1(" + p1.x + "," + p1.y + ") and p2(" + p2.x + "," +
            p2.y + ") = " + distance(p1, p2));
  }

    public static double distance (Point p1, Point p2){
      double x = p2.x - p1.x;
      double y = p2.y - p1.y;
      return Math.sqrt(Math.pow (x, 2) + Math.pow (y , 2));
    }
  }
