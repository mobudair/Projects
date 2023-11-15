package bugstompProject;

import bridges.games.NonBlockingGame;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import java.util.Random;

public class BugStomp extends NonBlockingGame {
  // Initial player location
  int row = 0, col = 0;

  // Sets the board size 20x20 
  static int[] boardSize = {20, 20};
  
  // Stores the bug's location
  int[] bug;
  
  // Bug's lifetime 
  int bugttl = 100; 
  
  // Player's score
  int score = 0;
  
  // Bug's Color
  NamedColor bugColor;
  
  Random randomizer;

  public static void main(String args[]) {
    // Initializes the nonblocking game
    BugStomp  mg = new BugStomp(2, "mobudair", "394206768657", boardSize[0], boardSize[1]);
  }

  // Constructor - setting up Bridges credentials, grid size
  public BugStomp(int assid, String login, String apiKey, int c, int r) {
    super(assid, login, apiKey, c, r);

    // A title and description of the game
    setTitle("BUG STOMP");
    setDescription("Use the arrow keys to move the cat over the bugs - don't let them escape!");

    // Starts running the game
    start();
  }

  // Initializes the board
  public void initialize() {
	  // Initializes random number generator
	  randomizer = new Random();
	  
	  // Colors in the board
	  for(int i =0; i <boardSize[0]; i++)
		  for (int j = 0; j < boardSize[1]; j++)
              setBGColor(i,j, NamedColor.blue);

	  bug = new int[] {
			  randomizer.nextInt(boardSize[0]), randomizer.nextInt(boardSize[1])
	  };
	// Sets bugColor to a NamedColor
	  bugColor = NamedColor.red;
	  
	  // Sets the bug's initial position using a random number limited by the 
	  // size of the board
	  drawSymbol(row, col, NamedSymbol.bug1, bugColor);
	  
  }

  // Movement of the player (human) - UI using arrow keys
  public void handleInput() {	 
	  	if (keyLeft()) {
	      col--;
	  	}
	  	
	  	if (keyRight()) {
	  		col++;
	  	}
	  	
	  	if (keyUp()) {
	  		row--;
	  	}
	  	
	  	if (keyDown()) {
	  		row++;
	  	}
	    
	    if (row < 0) {
	      row = 0;
	    }
	    if (row>boardSize[0]-1) {
	    	row = boardSize[0] -1;
	    }
	    if (col < 0 ) {
	    	col = 0;
	    }
	   
	    if (col>boardSize[1] - 1) {
	    	col = boardSize[1] - 1;
	    }
  }


  public void handlebug() {
	  if (bugttl < 1) {
		  // Finds a new random location for the bug's position
	      bug = new int[] {randomizer.nextInt(boardSize[0]), randomizer.nextInt(boardSize[1])};
		  
		  bugttl = randomizer.nextInt(100 - 50) + 50;
	      
		  // Decreases the score by one
	      
	      if (score < 0) {
	        score = 0;
	      }
	    }
	    else {
	      bugttl--;
	      // Calls the overlap function to see if the player got the bug
	      if (overlap(bug, row, col)) {
	    	// Creates a new position for the bug
	        bug = new int[] {
	        		randomizer.nextInt(boardSize[0]), randomizer.nextInt(boardSize[1])
	        };
	    	  
	    	// Increases the player's score by 1
	        score ++;
	      }
	    }
  }

  // Checks if the bug is squashed
  public Boolean overlap(int[] bug, int row, int col) {
	  // Checks the bug's position against the player's and returns true if they match and
	  // false if they don't
	    return Math.abs(bug[0]- row) < 2 && Math.abs(bug[1]-col) <2;
	  }

  
  // Redraws the screen with updated positions
  // Checks and paints the current score on the screen
  // If win, then it paints the winner on the screen
  public void paintScreen() {
	    for (int i = 0; i < boardSize[0]; ++i) {
	    	for(int j = 0; j <boardSize[1]; ++j) {
	    		setBGColor(i,j,NamedColor.black);
	    		drawSymbol(i,j,NamedSymbol.none, NamedColor.white);
	    	}

	    if(score >=10) {
	    	win();
	    	return;
	    }

	    // Paints the score
	   paintScore(score);

	    // Draws the bug
	    drawSymbol(bug[0], bug[1], NamedSymbol.bug3, bugColor);

	    // Draws the player
	    drawSymbol(row, col, NamedSymbol.cat, NamedColor.white);

  }
  }
  // Paints a win message
  public void win() {
	  // Draws the word "Winner", one letter at a time
	    drawSymbol(0,0,NamedSymbol.man,NamedColor.white);
	    drawSymbol(0,1,NamedSymbol.w, NamedColor.white);
	    drawSymbol(0,2,NamedSymbol.i, NamedColor.white);
	    drawSymbol(0,3,NamedSymbol.n, NamedColor.white);
	    drawSymbol(0,4,NamedSymbol.n, NamedColor.white);
	    drawSymbol(0,5,NamedSymbol.e, NamedColor.white);
	    drawSymbol(0,6,NamedSymbol.r, NamedColor.white);
	    drawSymbol(0,7,NamedSymbol.man,NamedColor.white);
	   
  }

  // Draws the current score on the screen
  public void paintScore(int score) {
	  // Writes the word "Score" on the screen starting with position 0,0
	    drawSymbol(0,0,NamedSymbol.S, NamedColor.white);
	    drawSymbol(0,1,NamedSymbol.c, NamedColor.white);
	    drawSymbol(0,2,NamedSymbol.o, NamedColor.white);
	    drawSymbol(0,3,NamedSymbol.r, NamedColor.white);
	    drawSymbol(0,4,NamedSymbol.e, NamedColor.white);
	    
	    // Draws the numeric score
	    drawSymbol(0, 6, NamedSymbol.values()[score + 53], NamedColor.white);
  }

  // Checks if the game ended
  public void gameLoop() {
    if (score >= 10) {
      System.exit(0);
    }

    handlebug();
    handleInput();
    paintScreen();
  }
}

