import javax.swing.*;
import java.awt.*;

public class RockPaperScissorsFrame extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsFrame());
    }

    JPanel mainPnl;
    JPanel buttonPnl;
    JPanel textPnl;
    JPanel statsPnl;
    JTextArea textArea;
    JScrollPane scrollPane;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;
    ImageIcon quitIcon;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    String playerMove;
    String computerMove;
    String result;
    int playerWins;
    int computerWins;
    int ties;


    public RockPaperScissorsFrame() {

        JPanel mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setTitle("Rock Paper Scissors Game");

        createBtnPanel();
        mainPnl.add(buttonPnl, BorderLayout.NORTH);
//        createTextPanel();
//        createStatsPanel();


        add(mainPnl);
    }

    private void createBtnPanel() {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 4));
        rockIcon = new ImageIcon("src/rock.png");
        paperIcon = new ImageIcon("src/paper.png");
        scissorsIcon = new ImageIcon("src/scissors.png");
        quitIcon = new ImageIcon("src/quit.png");

        rockBtn = new JButton(rockIcon);
        paperBtn = new JButton(paperIcon);
        scissorsBtn = new JButton(scissorsIcon);
        quitBtn = new JButton(quitIcon);

        quitBtn.addActionListener(e -> System.exit(0));

        buttonPnl.add(rockBtn);
        buttonPnl.add(paperBtn);
        buttonPnl.add(scissorsBtn);
        buttonPnl.add(quitBtn);
    }

    public String getPlayerMove() {

        return playerMove;
    }
}