/**
 * The GameFrame class represents the main frame of the game application.
 * It contains the main grid panel, side panel with control buttons and messages,
 * and logic for managing the game state.
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    // Panels for main grid, side panel, and control buttons
    private JPanel mainGridPanel = new JPanel();
    private JPanel sidePanel = new JPanel();
    private JPanel controlPanel = new JPanel();

    // Buttons representing the game grid and control buttons
    private JButton[][] gameButtons = new JButton[4][4];
    private JButton playButton = new JButton("Play");
    private JButton resetButton = new JButton("Reset");

    // Label for congratulatory messages
    private JLabel congratulationMessage = new JLabel("<html>Congratulations<br/> You won!</html>");

    // List to store game buttons
    private List<JButton> buttonList = new ArrayList<>();

    // Game logic instance
    private GameLogic gameLogic = new GameLogic(mainGridPanel, buttonList, congratulationMessage, gameButtons);

    // Stopwatch for tracking game time
    private Stopwatch timer;

    /**
     * Constructor for GameFrame class.
     * Sets up the layout, panels, buttons, and initializes game logic.
     */
    public GameFrame() {
        setLayout(new BorderLayout());

        mainGridPanel.setLayout(new GridLayout(4, 4, 0, 0));
        sidePanel.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout());

        add(mainGridPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        // Set dimensions and properties for side panel
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(150, 70));
        mainGridPanel.setPreferredSize(new Dimension(400, 400));

        // Set dimensions for control buttons
        playButton.setPreferredSize(new Dimension(300, 40));
        resetButton.setPreferredSize(new Dimension(30, 40));

        // Add control buttons to control panel
        controlPanel.add(playButton);
        controlPanel.add(resetButton);

        // Add congratulation message to side panel
        sidePanel.add(congratulationMessage);
        sidePanel.add(controlPanel, BorderLayout.SOUTH);

        // Hide congratulation message initially
        congratulationMessage.setVisible(false);

        // ActionListener for play button
        playButton.addActionListener(e -> {
            gameLogic.shuffle();
            if (timer != null) {
                timer.start();
            }
        });

        // ActionListener for reset button
        resetButton.addActionListener(e -> {
            gameLogic.resetButtons();
            if (timer != null) {
                timer.reset();
            }
        });

        // Create and add stopwatch to side panel
        timer = new Stopwatch();
        sidePanel.add(timer.getTimeLabel(), BorderLayout.NORTH);

        // Initialize game logic
        gameLogic.resetButtons();
        gameLogic.colours();

        // Set frame properties
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Main method to launch the game application.
     */
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
    }
}
