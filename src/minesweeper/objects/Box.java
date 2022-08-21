package minesweeper.objects;

public class Box {

    private boolean	bomb;
    private int		value;
    private int		x, y;
    private boolean	checked, flag;

    public Box(int x, int y) {
        bomb = false;
        checked = false;
        flag = false;
        value = 0;
        this.x = x;
        this.y = y;
    }

    public void increaseValue() {
        value++;
    }

    public boolean isBomb() {
        return bomb;
    }

    public boolean isNotBomb() {
        return !isBomb();
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int isValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int isX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int isY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isNotChecked() {
        return !isChecked();
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isFlag() {
        return flag;
    }

    public boolean isNotFlag() {
        return !isFlag();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
