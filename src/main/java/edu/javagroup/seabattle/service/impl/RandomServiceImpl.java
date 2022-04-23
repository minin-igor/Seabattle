package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.service.RandomService;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RandomServiceImpl implements RandomService {

    @Override
    public void writePanel() {

        int[][] array = new int[12][12];
        for (int i = 0; i < 12; i++) {
            array[0][i] = 2;
            array[11][i] = 2;
            array[i][0] = 2;
            array[i][11] = 2;
        }
        random(4, array);
        random(3, array);
        random(2, array);
        random(1, array);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (array[i][j] == 2) {
                    array[i][j] = 0;
                }
            }
        }
        int row = 1;
        List<HorizontalLine> horizontalLineList = MinePanelSingleton.instance(null).getPanel();
        for (HorizontalLine horizontalLine : horizontalLineList) {
            List<PointElement> pointElementList = horizontalLine.getPointElementList();
            int col = 1;
            for (PointElement pointElement : pointElementList) {
                pointElement.setValue(array[row][col]);
                col++;
            }
            row++;
        }
        MinePanelSingleton.instance(horizontalLineList);
    }

    private void random(int countDeck, int[][] array) {

        int count = 1;
        while (count <= 5 - countDeck) {
            int route = (int) (Math.random() * (1 + 1)) + 1;
            if (route == 1) {
                int row = (int) (Math.random() * (10 - countDeck + 1) + 1);
                int col = (int) (Math.random() * (9 + 1) + 1);
                if (verticalCheckByDeck(row, col, countDeck, array)) {
                    for (int i = 0; i < countDeck; i++) {
                        array[row + i][col] = 1;
                    }

                    for (int i = -1; i <= countDeck; i++) {
                        array[row + i][col + 1] = 2;
                        array[row + i][col - 1] = 2;
                    }
                    array[row - 1][col] = 2;
                    array[row + countDeck][col] = 2;
                    count++;
                }
            } else {
                int row = (int) (Math.random() * (9 + 1) + 1);
                int col = (int) (Math.random() * (10 - countDeck + 1) + 1);
                if (horizontalCheckByDeck(row, col, countDeck, array)) {
                    for (int i = 0; i < countDeck; i++) {
                        array[row][col + i] = 1;
                    }
                    for (int i = -1; i <= countDeck; i++) {
                        array[row + 1][col + i] = 2;
                        array[row - 1][col + i] = 2;
                    }
                    array[row][col - 1] = 2;
                    array[row][col + countDeck] = 2;
                    count++;
                }
            }
        }
    }

    private boolean verticalCheckByDeck(int row, int col, int countDeck, int[][] array) {
        if (countDeck == 4) {
            return true;
        } else if (countDeck == 3) {
            return array[row][col] == 0
                    && array[row + 1][col] == 0
                    && array[row + 2][col] == 0;
        } else if (countDeck == 2) {
            return array[row][col] == 0
                    && array[row + 1][col] == 0;
        } else if (countDeck == 1) {
            return array[row][col] == 0;
        } else return false;
    }

    private boolean horizontalCheckByDeck(int row, int col, int countDeck, int[][] array) {
        if (countDeck == 4) {
            return true;
        } else if (countDeck == 3) {
            return array[row][col] == 0
                    && array[row][col + 1] == 0
                    && array[row][col + 2] == 0;
        } else if (countDeck == 2) {
            return array[row][col] == 0
                    && array[row][col + 1] == 0;
        } else if (countDeck == 1) {
            return array[row][col] == 0;
        } else return false;
    }
}
