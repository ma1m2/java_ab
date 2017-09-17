package ru.msl.task2;

public class Launcher {
  public static void main(String[] args) {
    Point p1 = new Point(1,1);
    Point p2 = new Point(5,6);
    System.out.printf("Distance between two points is %.2f \n", p1.distance(p1,p2));
  }

/*  private static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
  }*/
}
