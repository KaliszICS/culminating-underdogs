public class WordleGame extends Game {
    private final String secret;
    private boolean win = false;

    public WordleGame(String word) {
        this.secret = word.toLowerCase();
    }

    public WordleGame() {
        this("apple");
    }

    @Override
    public String[] feedback(String attempt) {
        attempt = attempt.toLowerCase();
        String[] result = new String[5];
        boolean[] used = new boolean[5];

        for (int i = 0; i < 5; i++) {
            if (attempt.charAt(i) == secret.charAt(i)) {
                result[i] = "G";
                used[i] = true;
            } 
        }

        for(int i = 0; i < 5; i++) {
            if (result[i] == null) {
                boolean found = false;
                for (int j = 0; j < 5; j++) {
                    if(!used[j] && attempt.charAt(i) == secret.charAt(j)) {
                        found = true;
                        used[j] = true;
                        break;
                    }
                }
                result[i] = found ? "Y" : "X";
            }
        }

        tries++;
        if (attempt.equals(secret)) {
            win = true; 
            done = true;
        }
        else if (tries >= 6) {
            done = true;
        }
        return result;
    }

    public boolean isWin() {
        return win;
    }

    public String getSecret() {
        return secret;
    }
}