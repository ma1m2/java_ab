package ru.stqa.msl.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("my dear friend");
    hello("Alex");

    Square sq = new Square(5);
    System.out.println("Площадь квадрата со стороной " + sq.a + " = " + sq.area());

    Rectangle rec = new Rectangle(8, 5);
    System.out.println("Square of rectangle with sides " + rec.a +" and " + rec.b + " = " + rec.area());

    Point p1 = new Point(1,1);
    Point p2 = new Point(5,6);
    System.out.printf("Distance between two points is %.2f \n", p1.distance(p1,p2));

    p1 = new Point(2,1);
    p2 = new Point(4,20);
    System.out.printf("Distance between two points is %.2f \n", p1.distance(p1,p2));
  }

  private static void hello(String somebody){
    System.out.println("Hello, " + somebody + "!");
  }

  /*  private static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
  }*/
}
