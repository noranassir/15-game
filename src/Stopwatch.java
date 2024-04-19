/**
 * The Stopwatch class represents a simple stopwatch functionality.
 * It displays elapsed time in minutes and seconds.
 */
import java.awt.*;
import javax.swing.*;

import static java.awt.Font.BOLD;

public class Stopwatch {

    // JLabel to display the time
    JLabel timeLabel = new JLabel();
    // Variables to store time values
    int timePassed = 0;
    int seconds = 0;
    int minutes = 0;

    // String representations of time values with leading zeros
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);

    // Timer for updating the time display
    Timer timer;

    /**
     * Constructor for Stopwatch class.
     * Initializes the timer and sets up the time label.
     */
    public Stopwatch() {
        // Initialize timer with 1 second interval
        timer = new Timer(1000, e -> {

            // Update time values
            timePassed = timePassed + 1000;
            minutes = (timePassed / 60000) % 60;
            seconds = (timePassed / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);

            // Update time label text
            timeLabel.setText(minutes_string + ":" + seconds_string);

        });

        // Set initial time label text and font
        timeLabel.setText(minutes_string + ":" + seconds_string);
        timeLabel.setFont(new Font("Arial", BOLD, 35));
        // Align time label text to the center
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Retrieves the time label component.
     * @return The JLabel displaying the time.
     */
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    /**
     * Starts the stopwatch timer.
     */
    void start() {
        timer.start();
    }

    /**
     * Resets the stopwatch to its initial state.
     */
    void reset() {
        // Stop the timer
        timer.stop();
        // Reset time variables
        timePassed = 0;
        seconds = 0;
        minutes = 0;
        // Update time label text
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText(minutes_string + ":" + seconds_string);
    }
}
