package ru.nanikon.backend.area;

import java.util.function.BiPredicate;

/**
 * @author Natalia Nikonova
 */
public enum Quarter {
   FIRST((x, y) -> (x >= 0) && (y >= 0)),
   SECOND((x, y) -> (x <= 0) && (y >= 0)),
   THIRD((x, y) -> (x <= 0) && (y <= 0)),
   FOURTH((x, y) -> (x >= 0) && (y <= 0));

   final BiPredicate<Integer, Double> predicate;

   Quarter(BiPredicate<Integer, Double> predicate) {
      this.predicate = predicate;
   }

   public boolean check(Integer x, Double y) {
      return predicate.test(x, y);
   }
}
