import javax.swing.*;
import java.awt.*;

public class RockPaperScissorsFrame extends JFrame{

    JPanel mainPnl, buttonPnl, displayPnl, statsPnl;
    JTextArea resultsArea;
    JScrollPane scrollPane;
    ImageIcon rockIcon, paperIcon, scissorsIcon, quitIcon;
    JButton rockBtn, paperBtn, scissorsBtn, quitBtn;
    JLabel playerWinsLbl, computerWinsLbl, tiesLbl;
    JTextField playerWinsTxt, computerWinsTxt, tiesTxt;

    String playerMove;
    String computerMove;

    String playerLastMove = "";
    String computerLastMove = "";
    String strategyUsed = "";

    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;

    // Player total moves
    int playerRock = 0;
    int playerPaper = 0;
    int playerScissors = 0;

    // Computer total moves
    int computerRock = 0;
    int computerPaper = 0;
    int computerScissors = 0;

    CheatStrategy cheat = new CheatStrategy();
    RandomStrategy random = new RandomStrategy();

    class LeastUsedStrategy implements Strategy {
        @Override
        public String getMove(String playerMove) {
            int min = Math.min(playerRock, Math.min(playerPaper, playerScissors));
            if (min == playerRock) {
                return "rock";
            } else if (min == playerPaper) {
                return "paper";
            } else {
                return "scissors";
            }
        }
    }
    LeastUsedStrategy leastUsed = new LeastUsedStrategy();

    class MostUsedStrategy implements Strategy {
        @Override
        public String getMove(String playerMove) {
            int max = Math.max(playerRock, Math.max(playerPaper, playerScissors));
            if (max == playerRock) {
                return "rock";
            } else if (max == playerPaper) {
                return "paper";
            } else {
                return "scissors";
            }
        }
    }
    MostUsedStrategy mostUsed = new MostUsedStrategy();

    class LastUsedStrategy implements Strategy {
        @Override
        public String getMove(String playerMove) {
            if(playerLastMove.equals("")){
                return random.getMove(playerMove);
            } else {
                return playerLastMove;
            }
        }
    }
    LastUsedStrategy lastUsed = new LastUsedStrategy();

    public RockPaperScissorsFrame() {

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setTitle("Rock Paper Scissors Game");

        createBtnPanel();
        mainPnl.add(buttonPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createStatsPanel();
        mainPnl.add(statsPnl, BorderLayout.SOUTH);


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
        rockBtn.addActionListener(e -> {
            completeRound("rock");
            playerRock++;
        });

        paperBtn = new JButton(paperIcon);
        paperBtn.addActionListener(e -> {
            completeRound("paper");
            playerPaper++;
        });
        scissorsBtn = new JButton(scissorsIcon);
        scissorsBtn.addActionListener(e -> {
            completeRound("scissors");
            playerScissors++;
        });
        quitBtn = new JButton(quitIcon);

        quitBtn.addActionListener(e -> System.exit(0));

        buttonPnl.add(rockBtn);
        buttonPnl.add(paperBtn);
        buttonPnl.add(scissorsBtn);
        buttonPnl.add(quitBtn);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new BorderLayout());

        resultsArea = new JTextArea(15, 50);
        resultsArea.setEditable(false);
        scrollPane = new JScrollPane(resultsArea);

        displayPnl.add(scrollPane, BorderLayout.CENTER);
    }

    private void createStatsPanel() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(1, 6));

        playerWinsLbl = new JLabel("Player Wins: ");
        computerWinsLbl = new JLabel("Computer Wins: ");
        tiesLbl = new JLabel("Ties: ");

        playerWinsTxt = new JTextField(0 + "");
        playerWinsTxt.setEditable(false);
        computerWinsTxt = new JTextField(0 + "");
        computerWinsTxt.setEditable(false);
        tiesTxt = new JTextField(0 + "");
        tiesTxt.setEditable(false);

        statsPnl.add(playerWinsLbl);
        statsPnl.add(playerWinsTxt);
        statsPnl.add(computerWinsLbl);
        statsPnl.add(computerWinsTxt);
        statsPnl.add(tiesLbl);
        statsPnl.add(tiesTxt);
    }

    public String getComputerMove(String playerMove){
        java.util.Random rand = new java.util.Random();
        int prob = rand.nextInt(100) + 1;

        String computerMove = "";

        if (prob <= 10) {
            strategyUsed = "Cheat";
            computerMove = cheat.getMove(playerMove);
        }
        else if (prob <= 30) {
            strategyUsed = "Least Used";
            computerMove = leastUsed.getMove(playerMove);
        }
        else if (prob <= 50) {
            strategyUsed = "Most Used";
            computerMove = mostUsed.getMove(playerMove);
        }
        else if (prob <= 70) {
            strategyUsed = "Last Used";
            computerMove = lastUsed.getMove(playerMove);
        }
        else {
            strategyUsed = "Random";
            computerMove = random.getMove(playerMove);
        }

        switch(computerMove) {
            case "rock":
                computerRock++;
                computerLastMove = "rock";
                break;
            case "paper":
                computerPaper++;
                computerLastMove = "paper";
                break;
            case "scissors":
                computerScissors++;
                computerLastMove = "scissors";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }

    public void completeRound(String playerMove) {
        String computerMove = getComputerMove(playerMove);
        String result = "";

        resultsArea.append("Player chose: " + playerMove + ", Computer Used: " + computerMove + "\n");

        if (playerMove.equals("rock")){
            if (computerMove.equals("rock")){
                result = "Rock vs Rock: Tie!";
                ties++;
                tiesTxt.setText(ties + "");
            } else if (computerMove.equals("paper")){
                result = "Paper covers Rock: Computer wins!";
                computerWins++;
                computerWinsTxt.setText(computerWins + "");
            } else if (computerMove.equals("scissors")){
                result = "Rock crushes Scissors: Player wins!";
                playerWins++;
                playerWinsTxt.setText(playerWins + "");
            }
        }
        else if (playerMove.equals("paper")){
            if (computerMove.equals("rock")){
                result = "Paper covers Rock: Player wins!";
                playerWins++;
                playerWinsTxt.setText(playerWins + "");
            } else if (computerMove.equals("paper")){
                result = "Paper vs Paper: Tie!";
                ties++;
                tiesTxt.setText(ties + "");
            } else if (computerMove.equals("scissors")){
                result = "Scissors cut Paper: Computer wins!";
                computerWins++;
                computerWinsTxt.setText(computerWins + "");
            }
        }
        else if (playerMove.equals("scissors")){
            if (computerMove.equals("rock")){
                result = "Rock crushes Scissors: Computer wins!";
                computerWins++;
                computerWinsTxt.setText(computerWins + "");
            } else if (computerMove.equals("paper")){
                result = "Scissors cut Paper: Player wins!";
                playerWins++;
                playerWinsTxt.setText(playerWins + "");
            } else if (computerMove.equals("scissors")){
                result = "Scissors vs Scissors: Tie!";
                ties++;
                tiesTxt.setText(ties + "");
            }
        }


        resultsArea.append(result + " Computer used " + strategyUsed + " strategy.\n");

        playerLastMove = playerMove;
        computerLastMove = computerMove;
    }


}