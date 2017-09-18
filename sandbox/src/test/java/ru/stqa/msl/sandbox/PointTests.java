package ru.stqa.msl.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  private Point p1;
  private Point p2;

  @Test
  public void distanceTest1(){
    p1 = new Point(1.0, 5.0);
    p2 = new Point(2.0, 3.0);
    Assert.assertEquals(p1.distance(p1,p2), 2.23606797749979);
  }

  @Test
  public void distanceTest2(){
    p1 = new Point(1.0,1.0);
    p2 = new Point(5.0,6.0);
    Assert.assertEquals(p1.distance(p1,p2), 6.4031242374328485);
  }

  @Test
  public void distanceTest3(){
    p1 = new Point(2.0,1.0);
    p2 = new Point(4.0,20.0);
    Assert.assertEquals(p1.distance(p1,p2), 19.1049731745428);
  }
}
