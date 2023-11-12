package snakeProject;

import bridges.base.NamedColor;



import bridges.base.NamedSymbol;
import bridges.games.NonBlockingGame;

class Snake extends NonBlockingGame {
  // Game settings
  java.util.Random random = new java.util.Random();
  
  // Set dimensions of the game board
  static int gridColumns = 30;
  static int gridRows = 30;

  // Refresh rate
  final long FRAMERATE = 1000000000 / 15;
  long frameTime;
  long nextFrameTime;

  // Initial snake settings
  int startX = gridColumns / 3;
  int startY = gridRows / 2;
  int startLength = 3;
  
  // The background will alternate in color
  NamedColor bc1 = NamedColor.forestgreen;	//board color
  NamedColor bc2 = NamedColor.green;		//board color
  
  // Setting up other game colors
  NamedColor sc = NamedColor.silver;		//snake color
  NamedColor hc = NamedColor.white;			//head color
  NamedColor ac = NamedColor.red;			//apple color

  // These elements represent the snake's head and tail 
  Block head;
  Block tail;
  
  // This element represents the apple
  Block apple;

  // Keeps track of the snake's current and last direction 
  Direction dir;
  Direction lastDir;
  
  // Constructor - sets bridges credentials, grid size
  public Snake(int assid, String login, String apiKey, int c, int r) {
    super(assid, login, apiKey, c, r);
  }

  // Handles user input 
  public void handleInput() {
	// This if statement checks the key pressed by the user. It also checks if the current and last
	//directions don't equal the opposite direction
	  if (keyLeft() && dir != Direction.EAST && lastDir != Direction.EAST) {
		  dir = Direction.WEST;
	  } else if (keyUp() && dir != Direction.SOUTH && lastDir != Direction.SOUTH) {
		  dir = Direction.NORTH;
	  } else if (keyDown() && dir != Direction.NORTH && lastDir != Direction.NORTH) {
		  dir = Direction.SOUTH;
	  } else if (keyRight() && dir != Direction.WEST && lastDir != Direction.WEST) {
		  dir = Direction.EAST;
	  }
	  
  }

  // Updates snake position
  public void updatePosition() {
	// Move the snake one position, based on its direction and update
    // the linked list
	Block current = head.next; 
    int nextX = head.x;
    int nextY = head.y;
    Block nextPos = head;

    // Loops through the whole snake to move all of the blocks one space
    while (current != null) {
      int tempX = current.x;
      int tempY = current.y;
      current.x = nextX;
      current.y = nextY;
      nextX = tempX;
      nextY = tempY;
      current = current.next;
    }

    // This code ensures that the snake does not 
    // go off the edge of the board. It used the directions north,
    // south, east and west
    switch(dir) {
    case NORTH:
    head.y--;
    if(head.y <0)
    	head.y = gridRows -1;
    break;
    
    case SOUTH:
    	head.y++;
    	if(head.y == gridRows)
    		head.y = 0;
    	break;
    
    case EAST:
    	head.x++;
    	if(head.x == gridColumns)
    		head.x = 0;
    	break;
    	
    case WEST:
    head.x--;
    if(head.x<0)
    	head.x = gridColumns -1;
    break;
    }
 
  }

  // Creates a new apple (position)
  public void plantApple() {
	  int x;
	  int y;
	while (true) {
		  x = Math.abs(random.nextInt() % 29);
		  y = Math.abs(random.nextInt() % 29);
		  
		  boolean noCollison = true;
		  
		  Block current = head;
		while (current != null) {
			  if(current.x == x && current.y == y) {
				  noCollison = false;
				  break;
			  }
			  current = current.next;
		  }
		  if (noCollison);
		  break;
	  }
	apple.x = x;
	apple.y = y;
  }

  // Checks if the snake has found the apple
  public void detectApple() {
    // If apple is found, snake consumes it and update the board and plant
    // a new apple on the board.
	  
	// If the head's x/y equals the apple's x/y, a new tail
	// will be added and an apple will be planted
	  if (head.x == apple.x && head.y == apple.y) {
		  Block newTail = new Block(tail.x, tail.y);
		  drawSymbol(apple.y, apple.x, NamedSymbol.none, NamedColor.white); 
		  tail.next = newTail;
		  tail = newTail;
		  plantApple();
	  }
  }

// Checks if the snake ate itself
  public void detectDeath() {
	// Checks the snake's body and determines if the head's x/y
	// equals any of the current's x/y throughout the loop. If it does, 
	// Sytem.exit(0)
	  Block current = head.next;
	  while (current != null) {
		  if (head.x == current.x && head.y == current.y)
			  System.exit(0);
		  current = current.next;
	  }
  }

  // Redraw
  public void paint() {
    // Draws the board, the apple and the snake
	  for (int i = 0; i < gridColumns; ++i) {
		  for (int j = 0; j < gridRows; ++j) {
			  if (i % 2 == j %2)
				  setBGColor(i,j,bc1);
			  else
				  setBGColor(i,j,bc2);
		  }
	  }
	  setBGColor(head.y, head.x, hc);
	  
	  drawSymbol(apple.y, apple.x, NamedSymbol.apple, ac);
	  
	  Block current = head.next;
	  while (current != null) {
		  setBGColor(current.y, current.x, sc);
		  current = current.next;
	  }
  
  }

// Sets up the first state of the game grid
  public void initialize() {
    // Creates the snake of some number of elements,
    // performs all initializations and places the apple
	  for(int i =0; i < gridColumns; ++i) {
		  for(int j = 0; j <gridRows; ++j) {
			  if(i % 2 == j%2)
				  setBGColor(i,j,bc1);
			  else
				  setBGColor(i,j,bc2);
		  }
	  }
	
	// Background of the board 
	  head = new Block(startX, startY);
	// Sets head to a new block passing it the start x/y
	  Block current = head;
	
	  //loops through the snake based on the start length and colors
	  //the board accordingly
	  for (int i = 0; i < startLength; ++i) {
	    setBGColor(startY, startX - i, sc);
	    if (i > 0) {
	      current.next = new Block(startX - i, startY);
	      current = current.next;
	    }
	  }
	  tail = current;
	
	  frameTime = System.nanoTime();
	  nextFrameTime = frameTime + FRAMERATE + 10000;
	  dir = Direction.EAST;
	  lastDir = dir;
	  apple = new Block();
	  
	// Plants an apple
	  plantApple();
  }

  // Game loop will run many times per second.
  // handles input, check if apple was detected, update position, redraw,
  // detects if snake ate itself
  public void gameLoop() {
	  // Handles the input
	  handleInput();
	  if (System.nanoTime() > nextFrameTime) {
	    frameTime = System.nanoTime();
	    nextFrameTime = frameTime + FRAMERATE;

	    // Sets the last direction equal to direction
	    lastDir = dir;
	    // Detects an apple
	    detectApple();
	    // Changes/updates the position
	    updatePosition();
	    // Paints the screen
	    paint();
	    // Detects death
	    detectDeath();
	  }
  }

  public static void main(String args[]) {
    Snake game = new Snake(3, "mobudair", "394206768657",
                           gridColumns, gridRows);
    game.setTitle("Snake");

    game.start();
  }
}

enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST
}

// Helper class to hold snake and apple objects; snake grows as it
// eats and hence a linked list of blocks
class Block {
  public Block next;
  public int x;
  public int y;

  public Block() {
    this(-1, -1, null);
  }

  public Block(int x, int y) {
    this(x, y, null);
  }

  public Block(int x, int y, Block next) {
    this.x = x;
    this.y = y;
    this.next = next;
  }
}
