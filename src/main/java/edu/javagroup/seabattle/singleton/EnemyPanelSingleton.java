package edu.javagroup.seabattle.singleton;

import edu.javagroup.seabattle.model.HorizontalLine;

import java.util.List;

public class EnemyPanelSingleton {

    private static volatile EnemyPanelSingleton instance;
    private final List<HorizontalLine> horizontalLineList;

    private EnemyPanelSingleton(List<HorizontalLine> horizontalLineList) {
        this.horizontalLineList = horizontalLineList;
    }

    public static EnemyPanelSingleton instance(List<HorizontalLine> horizontalLineList) {
        synchronized (EnemyPanelSingleton.class) {
            if (horizontalLineList != null) {
                instance = new EnemyPanelSingleton(horizontalLineList);
            }
        }
        return instance;
    }

    public List<HorizontalLine> getPanel() {
        return horizontalLineList;
    }
}
