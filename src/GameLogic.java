import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GameLogic {

    private JPanel mainGridPanel;
    private List<JButton> buttonList;
    private JLabel congratulationMessage;
    private JButton[][] gameButtons;

    public GameLogic(JPanel mainGridPanel, List<JButton> buttonList, JLabel congratulationMessage, JButton[][] gameButtons) {
        this.mainGridPanel = mainGridPanel;
        this.buttonList = buttonList;
        this.congratulationMessage = congratulationMessage;
        this.gameButtons = gameButtons;
    }

    public void resetButtons() {
        mainGridPanel.removeAll();
        buttonList.clear();
        congratulationMessage.setVisible(false);

        int counter = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (counter <= 15) {
                    gameButtons[i][j] = new JButton(String.valueOf(counter++));
                    buttonList.add(gameButtons[i][j]);
                    gameButtons[i][j].addActionListener(new EventHandler(gameButtons[i][j], buttonList, mainGridPanel, congratulationMessage));
                } else {
                    gameButtons[i][j] = new JButton();
                    buttonList.add(gameButtons[i][j]);
                }

                gameButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
                gameButtons[i][j].setPreferredSize(new Dimension(60, 60));
                mainGridPanel.add(gameButtons[i][j]);
            }
        }
        colours();
        mainGridPanel.repaint();
        mainGridPanel.revalidate();
    }

    public void shuffle() {
        mainGridPanel.removeAll();
        Collections.shuffle(buttonList);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameButtons[i][j] = buttonList.get(i * 4 + j);
                mainGridPanel.add(gameButtons[i][j]);
            }
        }

        congratulationMessage.setVisible(false);
        mainGridPanel.repaint();
        mainGridPanel.revalidate();
    }

    public void colours() {
        int counter = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (counter % 2 == 0) {
                    gameButtons[i][j].setBackground(Color.BLACK);
                    gameButtons[i][j].setForeground(Color.WHITE);
                } else {
                    gameButtons[i][j].setBackground(Color.WHITE);
                }
                gameButtons[i][j].setOpaque(true);
                gameButtons[i][j].setBorderPainted(false);
                counter++;
            }
            counter++;
        }
    }
}
