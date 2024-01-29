import java.awt.*;
import javax.swing.*;

import static java.awt.Font.BOLD;

public class Stopwatch {

    JLabel timeLabel = new JLabel();
    int timePassed = 0;
    int seconds = 0;
    int minutes = 0;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);

    Timer timer;
    public Stopwatch() {
        timer = new Timer(1000, e -> {

            timePassed = timePassed + 1000;
            minutes = (timePassed / 60000) % 60;
            seconds = (timePassed / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);

            timeLabel.setText(minutes_string + ":" + seconds_string);

        });


        timeLabel.setText(minutes_string+ ":" +seconds_string);
        timeLabel.setFont(new Font("Arial", BOLD, 35));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    void start() {
        timer.start();
    }

    void reset() {
        timer.stop();
        timePassed = 0;
        seconds = 0;
        minutes = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText(minutes_string + ":" + seconds_string);
    }
}