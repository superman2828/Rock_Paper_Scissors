class CheatStrategy implements Strategy{
    @Override
    public String getMove(String playerMove) {
        String computerMove = "";
        switch (playerMove) {
            case "rock":
                computerMove = "paper";
                break;
            case "paper":
                computerMove = "scissors";
                break;
            case "scissors":
                computerMove = "rock";
                break;
            default:
                computerMove = "X";
                break;
        }
        return computerMove;
    }
}
