package edu.javagroup.seabattle.model;

import edu.javagroup.seabattle.model.parent.ModelRow;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorizontalLine extends ModelRow implements Comparable<HorizontalLine> {

    @Getter
    @Setter
    private List<PointElement> pointElementList = new ArrayList<>();

    public HorizontalLine(char row) {
        super(row);
        Collections.addAll(
                pointElementList,
                new PointElement(0, 1),
                new PointElement(0, 2),
                new PointElement(0, 3),
                new PointElement(0, 4),
                new PointElement(0, 5),
                new PointElement(0, 6),
                new PointElement(0, 7),
                new PointElement(0, 8),
                new PointElement(0, 9),
                new PointElement(0, 10)
        );
    }

    @Override
    public int compareTo(HorizontalLine o) {
        return this.getRow() - o.getRow();
    }
}
