package edu.javagroup.seabattle.init;

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.singleton.*;
import edu.javagroup.seabattle.constants.Constants;

import java.util.*;

public class Initializer {

    public void init() {
        SettingsSingleton.instance(new HashMap<>(0));
        initPanels();
    }

    public void initPanels() {

        MyStepSingleton.instance(true);
        ImReadySingleton.instance(false);
        EnemyReadySingleton.instance(false);

        Map<String, Boolean> forbiddenCellsMap = new HashMap<>(0);
        ForbiddenCellsSingleton.instance(forbiddenCellsMap);

        Map<String, Integer> shipStorageMap = new HashMap<>(4);
        shipStorageMap.put(1 + Constants.DECK, 0);
        shipStorageMap.put(2 + Constants.DECK, 0);
        shipStorageMap.put(3 + Constants.DECK, 0);
        shipStorageMap.put(4 + Constants.DECK, 0);
        ShipStorageSingleton.instance(shipStorageMap);

        List<HorizontalLine> mineHorizontalLineList = new ArrayList<>(10);
        Collections.addAll(
                mineHorizontalLineList,
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(0)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(1)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(2)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(3)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(4)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(5)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(6)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(7)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(8)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(9))
        );
        MinePanelSingleton.instance(mineHorizontalLineList);

        List<HorizontalLine> enemyHorizontalLineList = new ArrayList<>(10);
        Collections.addAll(
                enemyHorizontalLineList,
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(0)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(1)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(2)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(3)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(4)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(5)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(6)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(7)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(8)),
                new HorizontalLine(Constants.VERTICAL_COORDINATE.charAt(9))
        );
        EnemyPanelSingleton.instance(enemyHorizontalLineList);
    }
}

