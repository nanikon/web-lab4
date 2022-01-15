package ru.nanikon.backend.area;

/**
 * @author Natalia Nikonova
 */
public class VerticalRect extends Shape{
   public VerticalRect(Quarter quarter) {
      super(quarter);
   }

   @Override
   public boolean checkHit(Integer x, Double y, Integer r) {
      return super.checkHit(x, y, r) && (x >= -r / 2) && (x <= r / 2) && (y >= -r) && (y <= r);
   }
}
