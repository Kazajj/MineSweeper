package minesweeper.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import minesweeper.controller.Controller;
import minesweeper.element.GameListener;

public class ScreenView implements MouseListener, GameListener {

    private JPanel			panel;
    private JTextField		status;
    private JTextField		bombs;
    private JFrame			frame;
    private GameButton[][]	buttons;
    private Controller		controller;
    private final int		STATUS_BAR		= 150;
    private final String	REMAINING_BOMBS	= "Remaining bombs: ";

    public ScreenView(Controller controller) {
        this.controller = controller;
    }

    public void generateGamePanel() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setLocation(0, 0);
        panel.setPreferredSize(new Dimension(50 * controller.getX() + STATUS_BAR, 50 * controller.getY()));
        panel.setVisible(true);

        generateBoxes();
        generateStatus();

        frame.add(panel);
        frame.pack();

        frame.repaint();
    }

    public void generateBoxes() {
        buttons = new GameButton[controller.getX()][controller.getY()];
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j] = new GameButton();
                buttons[i][j].setCoordinateXandY(i, j);
                buttons[i][j].setLocation(50 * i, 50 * j);
                buttons[i][j].setVisible(true);
                buttons[i][j].addMouseListener(this);
                panel.add(buttons[i][j]);
            }
        }
    }

    public void generateStatus() {
        status = new JTextField();
        status.setSize(STATUS_BAR, controller.getY() * 49);
        status.setLocation(50 * controller.getX(), 50);
        status.setText(REMAINING_BOMBS + Integer.toString(controller.getBombs()));
        status.setVisible(true);
        panel.add(status);

        bombs = new JTextField();
        bombs.setSize(STATUS_BAR, 50);
        bombs.setLocation(50 * controller.getX(), 0);
        bombs.setText("Total bombs: " + Integer.toString(controller.getBombs()));
        bombs.setVisible(true);
        panel.add(bombs);
    }

    public void updateGraphic() {
        if (controller.isGameOver() || controller.isWin()) {
            gameoverGraphics();
            return;
        }
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                if (controller.getBox(i, j).isFlag()) {
                    buttons[i][j].setBackground(Color.CYAN);
                } else {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                    isChecked(i, j);
                }
            }
        }
        status.setText(REMAINING_BOMBS + Integer.toString(controller.getStatusBombs()));
        frame.repaint();
    }

    private void gameoverGraphics() {
        for (int i = 0; i < controller.getX(); i++) {
            for (int j = 0; j < controller.getY(); j++) {
                buttons[i][j].setEnabled(false);
                if (controller.getBox(i, j).isBomb()) {
                    if (controller.isWin()) {
                        buttons[i][j].setBackground(Color.GREEN);
                    } else {
                        buttons[i][j].setBackground(Color.RED);
                    }
                }
            }
        }
        frame.repaint();
    }

    private void isChecked(int i, int j) {
        if (controller.getBox(i, j).isChecked()) {
            buttons[i][j].setEnabled(false);
            buttons[i][j].setBackground(Color.WHITE);
            if (controller.getBox(i, j).isValue() != 0) {
                buttons[i][j].setText(String.valueOf(controller.getBox(i, j).isValue()));
            }
        }
    }

    // ///////////////////////////////////////////////////////////////////////////////F

    @Override
    public void mouseClicked(MouseEvent e) {
        GameButton gameButton = (GameButton) e.getSource();
        int x = gameButton.getCoordinateX();
        int y = gameButton.getCoordinateY();

        if (SwingUtilities.isLeftMouseButton(e)) {
            controller.onLeftClick(x, y);
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            controller.onRightClick(x, y);
        }
        updateGraphic();
    }

    @Override
    public void onTimeExpired() {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
