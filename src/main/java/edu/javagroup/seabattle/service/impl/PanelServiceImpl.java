package edu.javagroup.seabattle.service.impl;

import edu.javagroup.seabattle.constants.Constants;
import edu.javagroup.seabattle.model.HorizontalLine;
import edu.javagroup.seabattle.model.PointElement;
import edu.javagroup.seabattle.service.PanelService;
import edu.javagroup.seabattle.singleton.EnemyPanelSingleton;
import edu.javagroup.seabattle.singleton.MinePanelSingleton;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PanelServiceImpl implements PanelService {

    @Override
    public boolean isPanelEmpty() {
        int count = 0;
        for (HorizontalLine line : MinePanelSingleton.instance(null).getPanel()) {
            for (PointElement pointElement : line.getPointElementList()) {
                if (pointElement.getValue() != 0)
                    count++;
            }
        }
        return count == 0;
    }

    @Override
    public boolean isFullMinePanel() {

        int count = 0;
        for (HorizontalLine line : MinePanelSingleton.instance(null).getPanel()) {
            for (PointElement pointElement : line.getPointElementList()) {
                if (pointElement.getValue() == 1)
                    count++;
            }
        }
        return count == 20;
    }

    @Override
    public boolean checkEndGame(String side) {
        List<HorizontalLine> list = new ArrayList<>();
        if (side.equals(Constants.MINE) || side.equals(Constants.ENEMY)) {
            if (side.equals(Constants.MINE)) {
                list = MinePanelSingleton.instance(null).getPanel();
            }
            else {
                list = EnemyPanelSingleton.instance(null).getPanel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Проблема с выбором стороны", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        int count = 0;
        for (HorizontalLine line : list) {
            for (PointElement pointElement : line.getPointElementList()) {
                if (pointElement.getValue() == 2)
                    count++;
            }
        }
        return count == 20;
    }
}
