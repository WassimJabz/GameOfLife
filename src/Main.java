import java.awt.*;

public class Main {

    /**
     * Change these variables before running the program
     */
    final static double lifeProbability = 0.5; //The proportion of alive cells after randomizing at the beginning, between 0 and 1
    final static int size = 100; //The dimensions of the world, between 1 and 200
    final static int delay = 100; //The refresh rate of the program in milliseconds, must be positive
    final static Color ALIVE_COLOR = Color.WHITE; //The color of alive cells
    final static Color DEAD_COLOR = Color.BLACK; //The color of dead cells

    public static void main(String[] args){
        Graphics graphics = new Graphics(lifeProbability, size);
        while(true){
            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e){
                System.out.println("Failed to sleep");
            }
            graphics.evolve();
        }
    }
}
