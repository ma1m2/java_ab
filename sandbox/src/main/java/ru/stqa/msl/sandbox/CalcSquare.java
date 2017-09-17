package ru.stqa.msl.sandbox;

public class CalcSquare {

  public static void main(String[] args) {
    Square sq = new Square(5);
    System.out.println("Площадь квадрата со стороной " + sq.a + " = " + sq.area());

    Rectangle rec = new Rectangle(8, 5);
    System.out.println("Square of rectangle with sides " + rec.a +" and " + rec.b + " = " + rec.area());
  }

}
