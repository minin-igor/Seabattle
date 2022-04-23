package edu.javagroup.seabattle.singleton;

public class EnemyReadySingleton {

    private static volatile EnemyReadySingleton instance;
    private final Boolean enemyReady;

    public EnemyReadySingleton(Boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    public static EnemyReadySingleton instance(Boolean enemyReady) {
        synchronized (EnemyReadySingleton.class) {
            if (enemyReady != null) {
                instance = new EnemyReadySingleton(enemyReady);
            }
        }
        return instance;
    }

    public boolean enemyReady() {
        return enemyReady;
    }
}

