import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;


public class EventHandler implements ActionListener {
    JButton button;
    List<JButton> list;
    JPanel pl;
    JLabel message;
    updateGame update = new updateGame();
    public EventHandler(JButton pressedButton, List<JButton> list, JPanel panel, JLabel message) {
        button = pressedButton;
        this.list = list;
        pl = panel;
        this.message = message;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = list.indexOf(button);
        int j = getValidMove(i);
        if (!(j == -1)) {
            swap(list, i, j);
            update.updatePanel();
            update.winner();
        }
    }

    public int getValidMove(int i) {
        int leftB = i - 1;
        int rightB = i + 1;
        int downB = i + 4;
        int uppB = i - 4;

        if (!(i == 0) && list.get(leftB).getText().isEmpty()) { //kolla vänster
            if (!(leftB == 3) && !(leftB == 7) && !(leftB == 11)) {
                return leftB;
            }
        } else if (((rightB) < 16) && list.get(rightB).getText().isEmpty()) { //kolla höger
            if (!(rightB == 4) && !(rightB == 8) && !(rightB == 12)) {
                return rightB;
            }
        } else if ((uppB) >= 0 && list.get(uppB).getText().isEmpty()) { //kolla uppe
            return uppB;
        } else if ((downB) < 16 && list.get(downB).getText().isEmpty()) { // kolla nere
            return downB;
        }
        return -1;
    }

    private class updateGame {
        public void updatePanel() {
            pl.removeAll();
            for (JButton button : list) {
                pl.add(button);
            }
            pl.repaint();
            pl.revalidate();
        }

        public void winner() {
            if (list.get(15).getText().isEmpty()) {

                List<JButton> tmp = new ArrayList(list.subList(0, 15));
                int counter = 1;
                for (JButton b : tmp) {
                    if (b.getText().equals(String.valueOf(counter))) {
                        counter++;
                    } else {
                        return;
                    }
                }
                message.setVisible(true);
            }
        }
    }
}