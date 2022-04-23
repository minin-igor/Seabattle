package edu.javagroup.seabattle.singleton;

public class MyStepSingleton {

    private static volatile MyStepSingleton instance;
    private Boolean myStep;

    public MyStepSingleton(Boolean myStep) {
        this.myStep = myStep;
    }

    public static MyStepSingleton instance(Boolean myStep) {
        synchronized (MyStepSingleton.class) {
            if (myStep != null) {
                instance = new MyStepSingleton(myStep);
            }
        }
        return instance;
    }

    public Boolean myStep() {
        return myStep;
    }
}
