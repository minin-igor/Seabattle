package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.model.HorizontalLine;

import java.util.List;

public class MinePanelSingleton {

    private static volatile MinePanelSingleton instance;
    private final List<HorizontalLine> horizontalLineList;

    private MinePanelSingleton(List<HorizontalLine> horizontalLineList) {
        this.horizontalLineList = horizontalLineList;
    }

    public static MinePanelSingleton instance(List<HorizontalLine> horizontalLineList) {
        synchronized (MinePanelSingleton.class) {
            if (horizontalLineList != null) {
                instance = new MinePanelSingleton(horizontalLineList);
            }
        }
        return instance;
    }

    public List<HorizontalLine> getPanel() {
        return horizontalLineList;
    }
}
