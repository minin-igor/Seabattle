package edu.javagroup.seabattle.singleton;

import java.util.Map;

public class ImReadySingleton {

    private static volatile ImReadySingleton instance;
    private final Boolean imReady;

    public ImReadySingleton(Boolean imReady) {
        this.imReady = imReady;
    }

    public static ImReadySingleton instance(Boolean imReady) {
        synchronized (ImReadySingleton.class) {
            if (imReady != null) {
                instance = new ImReadySingleton(imReady);
            }
        }
        return instance;
    }

    public boolean imReady() {
        return imReady;
    }
}
