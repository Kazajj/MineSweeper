package minesweeper.controller;

import java.awt.Point;
import java.util.function.BiConsumer;

import minesweeper.element.GameListener;
import minesweeper.model.ModelGame;
import minesweeper.objects.Box;
import minesweeper.objects.GameState;

public class Controller {

    ModelGame		modelGame;
    GameListener	listener;

    public Controller(ModelGame modelGame) {
        this.modelGame = modelGame;
        this.listener = null;
    }

    public void chooseGridSize(int x, int y, int bombs) {
        modelGame.setX(x);
        modelGame.setY(y);
        modelGame.setBombs(bombs);
        modelGame.setStatusBomb(bombs);
        modelGame.generateBoxes();
        listener.generateGamePanel();
    }

    public void onLeftClick(int x, int y) {
        if (modelGame.isWin() || modelGame.isGameOver()) {
            return;
        }
        if (modelGame.isNotStarted() && getBox(x, y).isNotFlag()) {
            generateBombs(x, y);
            return;
        }
        if (getBox(x, y).isChecked() || getBox(x, y).isFlag()) {
            return;
        }
        if (getBox(x, y).isBomb()) {
            modelGame.setState(GameState.GAMEOVER);
            listener.updateGraphic();
            return;
        } else {
            checkBoxes(x, y);
            listener.updateGraphic();
            checkWin();
            listener.updateGraphic();
        }
    }

    private void generateBombs(int x, int y) {
        Point firstClick = new Point(x, y);
        for (int i = 0; i < modelGame.getBombs(); i++) {
            int xBomb = (int) (Math.random() * (double) getX());
            int yBomb = (int) (Math.random() * (double) getY());
            Point randomCoordinateBomb = new Point(xBomb, yBomb);
            if (boxNotAvailable(firstClick, randomCoordinateBomb) || getBox(xBomb, yBomb).isBomb()) {
                i--;
            } else {
                getBox(xBomb, yBomb).setBomb(true);
            }
        }
        modelGame.setState(GameState.STARTED);
        setBoxValues();
        clearBoxes(firstClick.x, firstClick.y);
        listener.updateGraphic();
    }

    private boolean boxAvailable(Point firstClick, Point randomCoordinateBomb) {
        int xFirstClick = (int) firstClick.getX();
        int yFirstClick = (int) firstClick.getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (randomCoordinateBomb.equals(new Point(xFirstClick + i, yFirstClick + j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean boxNotAvailable(Point firstClick, Point randomCoordinateBomb) {
        return !boxAvailable(firstClick, randomCoordinateBomb);
    }

    private void clearBoxes(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                checkBoxes(x + i, y + j);
            }
        }
    }

    private void setBoxValues() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (getBox(i, j).isBomb()) {
                    doubleFor((x, y) -> increaseBoxValue(x, y), i, j);
                }
            }
        }
    }

    private void increaseBoxValue(int x, int y) {
        if (isNot_ArrayIndexOutOfBounds(x, y)) {
            getBox(x, y).increaseValue();
        }
    }

    private void doubleFor(BiConsumer<Integer, Integer> biconsumer, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                biconsumer.accept(x + i, y + j);
            }
        }
    }

    public void checkBoxes(int x, int y) {
        if (isArrayIndexOutOfBounds(x, y)) {
            return;
        }
        if (getBox(x, y).isNotChecked() && getBox(x, y).isNotBomb()
                && getBox(x, y).isNotFlag()) {
            getBox(x, y).setChecked(true);
            if (getBox(x, y).isValue() == 0) {
                clearBoxes(x, y);
            }
        }
    }

    public void checkWin() {
        int counter = 0;
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                if (getBox(i, j).isNotBomb() && getBox(i, j).isChecked()) {
                    counter++;
                }
            }
        }
        if (counter == getX() * getY() - modelGame.getBombs()) {
            modelGame.setState(GameState.WIN);
        }
    }

    private boolean isArrayIndexOutOfBounds(int x, int y) {
        if (x < 0 || x >= getX() || y < 0 || y >= getY()) {
            return true;
        }
        return false;
    }

    private boolean isNot_ArrayIndexOutOfBounds(int x, int y) {
        return !isArrayIndexOutOfBounds(x, y);
    }

    public void onRightClick(int x, int y) {
        if (getBox(x, y).isChecked()) {
            return;
        }
        if (getBox(x, y).isFlag()) {
            getBox(x, y).setFlag(false);
            modelGame.increaseBombsStatus();
        } else {
            getBox(x, y).setFlag(true);
            modelGame.decreaseBombsStatus();
        }
    }

    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public Box getBox(int x, int y) {
        return modelGame.getBox(x, y);
    }

    public int getX() {
        return modelGame.getX();
    }

    public int getY() {
        return modelGame.getY();
    }

    public boolean isWin() {
        return modelGame.isWin();
    }

    public boolean isGameOver() {
        return modelGame.isGameOver();
    }

    public int getStatusBombs() {
        return modelGame.getStatusBombs();
    }

    public int getBombs() {
        return modelGame.getBombs();
    }

}
