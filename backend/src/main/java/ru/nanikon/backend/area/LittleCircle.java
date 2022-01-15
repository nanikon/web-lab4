package ru.nanikon.backend.area;

/**
 * @author Natalia Nikonova
 */
public class LittleCircle extends Circle {
    public LittleCircle(Quarter quarter) {
        super(quarter);
    }

    @Override
    public boolean checkHit(Integer x, Double y, Integer r) {
        return super.checkHit(x, y, r / 2);
    }
}
