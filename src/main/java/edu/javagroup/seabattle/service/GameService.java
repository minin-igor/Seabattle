package edu.javagroup.seabattle.service;

public interface GameService {

    boolean imReady();

    boolean enemyReady();

    void setShipPoint(char row, int col);

    boolean isFullMinePanel();

    boolean getBomb(char row, int col);

    void setPoint(String side, char row, int col, int value);

    boolean checkShipCount();

    boolean checkEndGame(String side);
}

