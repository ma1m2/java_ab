package ru.stqa.msl.sandbox;

public class Point {
  double x;
  double y;

  protected Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  protected double distance(Point p2){
    return Math.sqrt(Math.pow((this.x - p2.x),2) + Math.pow((this.y - p2.y),2));
  }
}
