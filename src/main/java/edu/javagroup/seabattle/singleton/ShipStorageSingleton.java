package edu.javagroup.seabattle.singleton;

import java.util.Map;

public class ShipStorageSingleton {

    private static volatile ShipStorageSingleton instance;
    private final Map<String, Integer> shipStorageMap;

    private ShipStorageSingleton(Map<String, Integer> shipStorageMap) {
        this.shipStorageMap = shipStorageMap;
    }

    public static ShipStorageSingleton instance(Map<String, Integer> shipStorageMap) {
        synchronized (ShipStorageSingleton.class) {
            if (shipStorageMap != null) {
                instance = new ShipStorageSingleton(shipStorageMap);
            }
        }
        return instance;
    }

    public Map<String, Integer> getShipMap() {
        return shipStorageMap;
    }
}

