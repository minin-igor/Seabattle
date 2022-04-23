package edu.javagroup.seabattle.singleton;

import java.util.Map;

public class ForbiddenCellsSingleton {

    private static volatile ForbiddenCellsSingleton instance;
    private final Map<String, Boolean> forbiddenCellsMap;

    private ForbiddenCellsSingleton(Map<String, Boolean> forbiddenCellsMap) {
        this.forbiddenCellsMap = forbiddenCellsMap;
    }

    public static ForbiddenCellsSingleton instance(Map<String, Boolean> forbiddenCellsMap) {
        synchronized (ForbiddenCellsSingleton.class) {
            if (forbiddenCellsMap != null) {
                instance = new ForbiddenCellsSingleton(forbiddenCellsMap);
            }
        }
        return instance;
    }

    public Map<String, Boolean> getForbiddenCellsMap() {
        return forbiddenCellsMap;
    }

}
