public class RandomStrategy implements Strategy {
    @Override
    public String getMove(String playerMove) {

        RandomStrategy rand = new RandomStrategy();
        java.util.Random random = new java.util.Random();
        String computerMove = "";
        int randomNum = random.nextInt(3);
        switch (randomNum) {
            case 0:
                computerMove = "rock";
                break;
            case 1:
                computerMove = "paper";
                break;
            case 2:
                computerMove = "scissors";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }

}
