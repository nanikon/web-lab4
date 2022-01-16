package ru.nanikon.backend.area;

/**
 * @author Natalia Nikonova
 */
public class HorizontalRhomb extends Shape{
   public HorizontalRhomb(Quarter quarter) {
      super(quarter);
   }

   @Override
   public boolean checkHit(Integer x, Double y, Integer r) {
      return super.checkHit(x, y, r)
              && (y <= (double) (r - x) / 2)
              && (y >= (double) (x - r) / 2)
              && (y >= (double) -(x + r) / 2)
              && (y <= (double) (x + r) / 2);
   }
}
