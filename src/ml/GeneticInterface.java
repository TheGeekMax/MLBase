package ml;

public interface GeneticInterface {
    public void play(float[] output);
    public float[] getInput();
    public boolean isFinished();
    public int getScore();
    public void reset();
}
