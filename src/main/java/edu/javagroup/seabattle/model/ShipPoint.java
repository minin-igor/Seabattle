package edu.javagroup.seabattle.model;

import edu.javagroup.seabattle.model.parent.ModelValue;
import lombok.Getter;

public class ShipPoint extends ModelValue implements Comparable<ShipPoint>{

    @Getter
    private final int point;


    public ShipPoint(int point, int value) {
        super(value);
        this.point = point;
    }

    @Override
    public int compareTo(ShipPoint o) {
        return this.point - o.point;
    }
}
