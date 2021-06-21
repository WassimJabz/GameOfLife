import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graphics {

    final Dimension SIZE = new Dimension(900, 900);
    JFrame gameFrame; //The window that will launch whenever we run the program
    BoardPanel boardPanel; //To represent the squares of the world
    Game game; //The underlying game instance

    public Graphics(double lifeProbability, int size){
        game = new Game(lifeProbability, size);
        gameFrame = new JFrame();
        boardPanel = new BoardPanel();
        gameFrame.add(boardPanel, BorderLayout.CENTER);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(SIZE);
        gameFrame.setVisible(true);
        gameFrame.validate();
    }

    public class BoardPanel extends JPanel{

        ArrayList<Cell> listOfCells = new ArrayList<Cell>(); //To store all the cells of the game in one place

        public BoardPanel(){

            super(new GridLayout(game.world.length, game.world[0].length));

            for(int i = 0; i < game.world.length; i++){
                for(int j = 0; j < game.world.length; j++){
                    Cell cell = new Cell(i, j, game.world[i][j]);
                    listOfCells.add(cell);
                    add(cell);
                }
            }

            setPreferredSize(SIZE);
            validate();
        }

        public void refreshBoard(){
            for(Cell cell: listOfCells) cell.refresh();
        }
    }

    public class Cell extends JPanel{

        int x;
        int y;
        boolean state;

        public Cell(int x, int y, boolean state) {
            super(new GridBagLayout());
            this.x = x;
            this.y = y;
            this.state = state;
            if (!state) setBackground(Main.DEAD_COLOR);
            else setBackground(Main.ALIVE_COLOR);
        }

        public void refresh(){
            state = game.world[x][y];
            if(!state) setBackground(Main.DEAD_COLOR);
            else setBackground(Main.ALIVE_COLOR);
        }

    }

    public void evolve(){
        game.evolve();
        boardPanel.refreshBoard();
    }
}
