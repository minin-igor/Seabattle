package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.constants.Constants;
import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.service.PointService;
import edu.javagroup.seabattle.singleton.EnemyPanelSingleton;
import edu.javagroup.seabattle.singleton.ForbiddenCellsSingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import edu.javagroup.seabattle.singleton.MyStepSingleton;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.*;

@Component
public class PointServiceImpl implements PointService {

    private final PanelService panelService;

    public PointServiceImpl(PanelService panelService) {
        this.panelService = panelService;
    }

    @Override
    public void setShipPoint(char row, int col) {
        if (isClearPoint(row, col)) {
            addShipPoint(row, col);
        } else {
            clearShipPoint(row, col);
        }
    }

    @Override
    public boolean setPoint(String side, char row, int col, int value) {
        List<HorizontalLine> list = new ArrayList<>(10);
        if (side.equals(Constants.MINE) || side.equals(Constants.ENEMY)) {
            if (side.equals(Constants.MINE)) {
                list = MinePanelSingleton.instance(null).getPanel();
            } else {
                list = EnemyPanelSingleton.instance(null).getPanel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Проблема с выбором стороны", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        for (HorizontalLine horizontalLine : list) {
            if (horizontalLine.getRow() == row) {
                for (PointElement element : horizontalLine.getPointElementList()) {
                    if (element.getCol()  == col) {
                        element.setValue(value);
                        if (side.equals(Constants.MINE)) {
                            MinePanelSingleton.instance(list);
                        } else {
                            EnemyPanelSingleton.instance(list);
                        }
                        return true;
                    }
                }
            }
        }
        return false;

    }

    @Override
    public boolean isClearPoint(char row, int col) {
        return isOccupiedCell(row, col, 0);
    }

    @Override
    public boolean getBomb(char row, int col) {
        if (isOccupiedCell(row, col, 0) || isOccupiedCell(row, col, 2)) {
            setPoint(Constants.MINE, row, col, 3);
            MyStepSingleton.instance(true);
        }
        if (isOccupiedCell(row, col, 1)) {
            setPoint(Constants.MINE, row, col, 3);
            MyStepSingleton.instance(false);
            return true;
        }
        return false;
    }

    private boolean isOccupiedCell(char row, int col, int value) {
        for (HorizontalLine horizontalLine : MinePanelSingleton.instance(null).getPanel()) {
                for (PointElement element : horizontalLine.getPointElementList()) {
                    if (horizontalLine.getRow() == row && element.getCol() == col && element.getValue() == value) {
                        return true;
                    }
                }
            }
        return false;
    }

    private void addShipPoint(char row, int col) {
        Map<String, Boolean> forbiddenCellsMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        if (!forbiddenCellsMap.containsKey(row + checkCol(col) + col)) {
            if (!panelService.isFullMinePanel()) {
               if (setPoint(Constants.MINE, row, col, 1)) {
                   setForbiddenCells(row, col);
               } else {
                   JOptionPane.showMessageDialog(null, "Нельзя использовать эту ячейку",
                           "Внимание!", JOptionPane.WARNING_MESSAGE);
               }
            } else {
                JOptionPane.showMessageDialog(null, "Уже занято допустимое количество ячеек",
                        "Внимание!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Не удалось использовать эту ячейку",
                    "Внимание!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearShipPoint(char row, int col) {
        if (setPoint(Constants.MINE, row, col, 1)) {
            clearAroundPoints(row, col);
        }
        if (panelService.isPanelEmpty()) {
            ForbiddenCellsSingleton.instance(new HashMap<>(0));
        }
    }

    private void setForbiddenCells(char row, int col) {
        Map<String, Boolean> forbiddenCellsMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        forbiddenCellsMap.put(row + checkCol(col) + col, false);
        forbiddenCellsMap.put(getPreviousLine(row) + checkCol(col) + (col + 1), false);
        forbiddenCellsMap.put(getPreviousLine(row) + checkCol(col) + (col - 1), false);
        forbiddenCellsMap.put(getNextLine(row) + checkCol(col) + (col + 1), false);
        forbiddenCellsMap.put(getNextLine(row) + checkCol(col) + (col - 1), false);
    }

    private void clearAroundPoints(char row, int col) {
        Map<String, Boolean> forbiddenCellsMap = ForbiddenCellsSingleton.instance(null).getForbiddenCellsMap();
        forbiddenCellsMap.put(row + checkCol(col) + col, true);
        forbiddenCellsMap.put(getPreviousLine(row) + checkCol(col) + (col + 1), true);
        forbiddenCellsMap.put(getPreviousLine(row) + checkCol(col) + (col - 1), true);
        forbiddenCellsMap.put(getNextLine(row) + checkCol(col) + (col + 1), true);
        forbiddenCellsMap.put(getNextLine(row) + checkCol(col) + (col - 1), true);
    }

    private char getPreviousLine (char letter) {
        return (char) (letter - 1);
    }

    private char getNextLine (char letter) {
        return (char) (letter + 1);
    }

    private String checkCol (int col) {
        if (col == 10) {
            return "";
        } else return "0";
    }

}
