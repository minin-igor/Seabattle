package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.model.ShipPoint;
import edu.javagroup.seabattle.service.ShipService;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.ShipStorageSingleton;
import org.springframework.stereotype.Component;

import java.util.*;

import static edu.javagroup.seabattle.constants.Constants.DECK;

@Component
public class ShipServiceImpl implements ShipService {

    private List<ShipPoint> coordinateList;

    @Override
    public boolean checkShipCount() {
        List<HorizontalLine> lineList = MinePanelSingleton.instance(null).getPanel();
        getCoordinateList(lineList);
        Map<String, Integer> shipMap = new HashMap<>();
        shipMap.put("4" + DECK, findShipDeck(4));
        shipMap.put("3" + DECK, findShipDeck(3));
        shipMap.put("2" + DECK, findShipDeck(2));
        shipMap.put("1" + DECK, findShipDeck(1));
        ShipStorageSingleton.instance(shipMap);
        return shipMap.get("4" + DECK) == 1
                && shipMap.get("3" + DECK) == 2
                && shipMap.get("2" + DECK) == 3
                && shipMap.get("1" + DECK) == 24 ;
    }

    private List<ShipPoint> getCoordinateList(List<HorizontalLine> panel) {
        coordinateList = new ArrayList<>(221);
        coordinateList.addAll(getHorizontalCoordinateList(panel));
        coordinateList.addAll(getVerticalCoordinateList(panel));
        List<ShipPoint> shipPointList = new ArrayList<>();
        for (int i = 0; i < coordinateList.size()-1; i++) {
            if (coordinateList.get(i).getValue() == 0 && coordinateList.get(i + 1).getValue() == 0) {
                shipPointList.add(coordinateList.get(i));
            }
        }
        coordinateList.removeAll(shipPointList);
        coordinateList.sort(Comparator.comparing(ShipPoint::getPoint));
        return coordinateList;
    }

    private List<ShipPoint> getHorizontalCoordinateList(List<HorizontalLine> panel) {
        List<ShipPoint> shipPointList = new ArrayList<>(110);
        int point = 1;
        for (HorizontalLine horizontalLine : panel) {
            List<PointElement> pointElementList = horizontalLine.getPointElementList();
            for (PointElement pointElement : pointElementList) {
                shipPointList.add(new ShipPoint(point, pointElement.getValue()));
                point++;
            }
            if (shipPointList.size() < 109) {
                shipPointList.add(new ShipPoint(point, 0));
                point++;
            }
        }
        shipPointList.add(new ShipPoint(point, 0));
        return shipPointList;
    }

    private List<ShipPoint> getVerticalCoordinateList(List<HorizontalLine> panel) {
        List<ShipPoint> shipPointList = new ArrayList<>(110);
        int col = 1;
        int point = 111;
        while (col <= 10) {
            for (HorizontalLine horizontalLine : panel) {
                List<PointElement> pointElementList = horizontalLine.getPointElementList();
                for (PointElement pointElement : pointElementList) {
                    if (pointElement.getCol() == col) {
                        shipPointList.add(new ShipPoint(point, pointElement.getValue()));
                        point++;
                    }
                }
            }
            if (shipPointList.size() <= 110) {
                shipPointList.add(new ShipPoint(point, 0));
                point++;
                col++;
            }
        }
        shipPointList.add(new ShipPoint(point, 0));
        for (ShipPoint shipPoint : shipPointList) {
            System.out.println(shipPoint.getPoint());
        }
        return shipPointList;
    }

    private int findShipDeck(int deckCount) {
        StringBuilder builder = new StringBuilder();
        for (ShipPoint shipPoint : coordinateList) {
            builder.append(shipPoint.getValue());
        }
        String[] array = builder.toString().split("0");
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (deckCount == array[i].length()) {
                count++;
            }
        }
        return count;
    }
}
