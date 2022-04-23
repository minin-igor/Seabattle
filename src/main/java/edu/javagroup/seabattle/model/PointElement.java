package edu.javagroup.seabattle.model;

import edu.javagroup.seabattle.model.parent.ModelValue;
import lombok.Getter;

public class PointElement extends ModelValue implements Comparable<PointElement> {
    @Getter
    private final int col;

    public PointElement(int value, int col) {
        super(value);
        this.col = col;
    }

    @Override
    public int compareTo(PointElement o) {
        return this.col - o.col;
    }
}
