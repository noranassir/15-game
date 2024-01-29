import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    private JPanel mainGridPanel = new JPanel();
    private JPanel sidePanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JButton[][] gameButtons = new JButton[4][4];
    private JButton playButton = new JButton("Play");
    private JButton resetButton = new JButton("Reset");
    private JLabel congratulationMessage = new JLabel("<html>Congratulations<br/> You won!</html>");
    private List<JButton> buttonList = new ArrayList<>();
    private GameLogic gameLogic = new GameLogic(mainGridPanel, buttonList, congratulationMessage, gameButtons);
    private Stopwatch timer;

    public GameFrame() {
        setLayout(new BorderLayout());

        mainGridPanel.setLayout(new GridLayout(4, 4, 0, 0));
        sidePanel.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout());

        add(mainGridPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        sidePanel.setBackground(Color.WHITE);
        sidePanel.setPreferredSize(new Dimension(150, 70));
        mainGridPanel.setPreferredSize(new Dimension(400, 400));

        playButton.setPreferredSize(new Dimension(300, 40));
        resetButton.setPreferredSize(new Dimension(30, 40));

        controlPanel.add(playButton);
        controlPanel.add(resetButton);

        sidePanel.add(congratulationMessage);
        sidePanel.add(controlPanel, BorderLayout.SOUTH);

        congratulationMessage.setVisible(false);

        playButton.addActionListener(e -> {
            gameLogic.shuffle();
            if (timer != null) {
                timer.start();
            }
        });
        resetButton.addActionListener(e -> {
            gameLogic.resetButtons();
            if (timer != null) {
                timer.reset();
            }
        });

        timer = new Stopwatch();
        sidePanel.add(timer.getTimeLabel(), BorderLayout.NORTH);

        gameLogic.resetButtons();
        gameLogic.colours();

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
    }
}
