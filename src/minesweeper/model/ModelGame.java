package minesweeper.model;

import minesweeper.objects.Box;
import minesweeper.objects.GameState;

public class ModelGame {
    private int			x, y, bombs;
    private GameState	state;
    private Box[][]		boxes;
    private int			statusBombs;

    public ModelGame() {
        state = GameState.WAITING;
    }

    public void generateBoxes() {
        boxes = new Box[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boxes[i][j] = new Box(i, j);
            }
        }
    }

    public Box getBox(int x, int y) {
        return boxes[x][y];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public Box[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public boolean isWin() {
        if (state == GameState.WIN) {
            return true;
        }
        return false;
    }

    public boolean isStarted() {
        if (state == GameState.STARTED) {
            return true;
        }
        return false;
    }

    public boolean isNotStarted() {
        return !isStarted();
    }

    public boolean isWaiting() {
        if (state == GameState.WAITING) {
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        if (state == GameState.GAMEOVER) {
            return true;
        }
        return false;
    }

    public void increaseBombsStatus() {
        statusBombs++;
    }

    public void decreaseBombsStatus() {
        statusBombs--;
    }

    public int getStatusBombs() {
        return statusBombs;
    }

    public void setStatusBomb(int bombs) {
        statusBombs = bombs;
    }
}
