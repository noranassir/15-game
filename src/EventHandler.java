/**
 * The EventHandler class handles button click events in the game grid.
 * It implements the ActionListener interface to respond to button clicks.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class EventHandler implements ActionListener {
    // Reference to the clicked button
    JButton button;
    // List of buttons representing the game grid
    List<JButton> list;
    // Panel containing the game grid
    JPanel pl;
    // Label for displaying game messages
    JLabel message;
    // Instance of updateGame for updating the game state
    updateGame update = new updateGame();

    /**
     * Constructor for EventHandler class.
     * Initializes the EventHandler with the clicked button, button list, panel, and message label.
     * @param pressedButton The button that was clicked.
     * @param list The list of buttons representing the game grid.
     * @param panel The panel containing the game grid.
     * @param message The label for displaying game messages.
     */
    public EventHandler(JButton pressedButton, List<JButton> list, JPanel panel, JLabel message) {
        button = pressedButton;
        this.list = list;
        pl = panel;
        this.message = message;
    }

    /**
     * Handles button click events.
     * Moves the clicked button if a valid move is available and updates the game state.
     * @param e The ActionEvent representing the button click.
     */
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

    /**
     * Determines if a valid move is available for the clicked button.
     * Checks neighboring buttons to determine valid moves.
     * @param i The index of the clicked button in the button list.
     * @return The index of the valid move button, or -1 if no valid move is available.
     */
    public int getValidMove(int i) {
        int leftB = i - 1;
        int rightB = i + 1;
        int downB = i + 4;
        int uppB = i - 4;

        if (!(i == 0) && list.get(leftB).getText().isEmpty()) { // Check left
            if (!(leftB == 3) && !(leftB == 7) && !(leftB == 11)) {
                return leftB;
            }
        } else if (((rightB) < 16) && list.get(rightB).getText().isEmpty()) { // Check right
            if (!(rightB == 4) && !(rightB == 8) && !(rightB == 12)) {
                return rightB;
            }
        } else if ((uppB) >= 0 && list.get(uppB).getText().isEmpty()) { // Check up
            return uppB;
        } else if ((downB) < 16 && list.get(downB).getText().isEmpty()) { // Check down
            return downB;
        }
        return -1;
    }

    /**
     * The updateGame class contains methods to update the game state.
     */
    private class updateGame {
        /**
         * Updates the game panel with the current button positions.
         */
        public void updatePanel() {
            pl.removeAll();
            for (JButton button : list) {
                pl.add(button);
            }
            pl.repaint();
            pl.revalidate();
        }

        /**
         * Checks if the game has been won and displays a message if true.
         */
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
