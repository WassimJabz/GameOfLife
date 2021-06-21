import java.util.Random;

public class Game {

    /**
     * Variables
     **/

    boolean[][] world; //True = Alive, False = Dead
    double lifeProbability;
    final Random rgen = new Random();


    /**
     * Methods
     */

    //Constructor that randomizes the world based on the probability passed as input
    public Game(double lifeProbability, int size){
        if(lifeProbability < 0 || lifeProbability > 1) throw new IllegalArgumentException("The probability must be between 0 and 1");
        if(size < 1 || size > 200) throw new IllegalArgumentException("The grid must have a size between 1 and 200 ");
        this.world = new boolean[size][size];
        this.lifeProbability = lifeProbability;
        randomize();
    }

    //To randomize the world before beginning the game
    public void randomize(){
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){
                if(rgen.nextDouble() < lifeProbability) world[i][j] = true;
                else world[i][j] = false;
            }
        }
    }

    /* To apply the rules and go to the next generation
     * The rules are as follows:
     * 1- Any live cell with fewer than two live neighbors dies, as if caused by under population.
     * 2- Any live cell with two or three live neighbors lives on to the next generation.
     * 3- Any live cell with more than three live neighbors dies, as if by overpopulation.
     * 4- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
    public void evolve(){

        boolean[][] nextWorld = new boolean[world.length][world[0].length]; //Will be used to store the next generation

        //Iterating over every cell in the current generation and determining its state for the next generation
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){

                int aliveNeighbors = countLiveNeighbors(i,j);

                //If the cell is alive, apply 1-2-3
                if(world[i][j]){
                    if(aliveNeighbors < 2) nextWorld[i][j] = false;
                    if(aliveNeighbors == 2 || aliveNeighbors == 3) nextWorld[i][j] = true;
                    if(aliveNeighbors > 3) nextWorld[i][j] = false;
                }

                //If the cell is dead apply 4
                else{
                    if(aliveNeighbors == 3) nextWorld[i][j] = true;
                }
            }
        }

        world = nextWorld; //Moving on to the next generation
    }

    //Returns the number of live neighbors the cell at coordinates i,j has
    public int countLiveNeighbors(int i, int j){

        int counter = 0;

        //Checking each of the 8 neighboring cells
        if(i-1 >= 0 && world[i-1][j]) counter++;
        if(i-1 >= 0 && j-1 >= 0 && world[i-1][j-1]) counter++;
        if(j-1 >= 0 && world[i][j-1]) counter++;
        if(i+1 < world.length && j-1 >= 0 && world[i+1][j-1]) counter++;
        if(i+1 < world.length && world[i+1][j]) counter++;
        if(i+1 < world.length && j+1 < world[0].length && world[i+1][j+1]) counter++;
        if(j+1 < world[0].length && world[i][j+1]) counter++;
        if(i-1 >= 0 && j+1 < world[0].length && world[i-1][j+1]) counter++;

        return counter;
    }
}
