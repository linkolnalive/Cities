public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            int score = game.play();
            System.out.println(score > -1 ? "Player Wins.\nScore: " + score : "AI Wins");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

