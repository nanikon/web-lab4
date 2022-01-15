package ru.nanikon.backend.service;

import org.springframework.stereotype.Service;
import ru.nanikon.backend.area.Shape;
import ru.nanikon.backend.entity.ShotEntity;

import java.util.ArrayList;

/**
 * @author Natalia Nikonova
 */
@Service
public class AreaService {
    private final ArrayList<Shape> parts = new ArrayList<>();

    public void addShape(Shape shape) {
        parts.add(shape);
    }

    public void checkArea(ShotEntity shot) {
        boolean result = false;
        for (Shape shape : parts) {
            result = result || shape.checkHit(shot.getX(), shot.getY(), shot.getR());
        }
        shot.setIsHit(result);
    }
}
