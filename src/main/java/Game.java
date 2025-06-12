public abstract class Game {
    protected boolean done = false;
    protected int tries = 0;

    public abstract String[] feedback(String guess);

    public Boolean isDone() {
        return done;
    }

    public int getTries() {
        return tries;
    }

    public void reset() {
        done = false;
        tries = 0;
    }
}