package minesweeper;

import minesweeper.controller.Controller;
import minesweeper.element.GameListener;
import minesweeper.graphic.ScreenView;
import minesweeper.model.ModelGame;

public class MineSweeper {

    public static void main(String[] args) {
        int x = 20, y = 20, bombs = 50;

        ModelGame modelGame = new ModelGame();
        Controller controller = new Controller(modelGame);
        GameListener listener = new ScreenView(controller);
        controller.setListener(listener);

        controller.chooseGridSize(x, y, bombs);
    }
}
