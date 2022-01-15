package ru.nanikon.backend.area;

/**
 * @author Natalia Nikonova
 */
abstract public class Shape {
    private final Quarter quarter;

    public Shape(Quarter quarter) {
        this.quarter = quarter;
    }

    public boolean checkHit(Integer x, Double y, Integer r) {
        return quarter.check(x, y);
    }
}
