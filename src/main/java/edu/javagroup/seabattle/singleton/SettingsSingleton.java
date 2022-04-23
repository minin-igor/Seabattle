package edu.javagroup.seabattle.singleton;

import java.util.Map;

public class SettingsSingleton {

    private static volatile SettingsSingleton instance;
    private final Map<String, String> settingMap;

    private SettingsSingleton(Map<String, String> settingMap) {
        this.settingMap = settingMap;
    }

    public static SettingsSingleton instance(Map<String, String> settingMap) {
        synchronized (SettingsSingleton.class) {
            if (settingMap != null) {
                instance = new SettingsSingleton(settingMap);
            }
        }
        return instance;
    }

    public Map<String, String> getSettingsMap() {
        return settingMap;
    }

    public String getSettingsByKey(String key) {
        return settingMap.getOrDefault(key, "");
    }
}
