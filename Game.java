
public class Game {
	 private Parser parser;
	    private Room currentRoom;
	        
	    /**
	     * Create the game and initialise its internal map.
	     */
	    public Game() 
	    {
	        createRooms();
	        parser = new Parser();
	    }

	    /**
	     * Create all the rooms and link their exits together.
	     */
	    private void createRooms()
	    {
	        Room marketsquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach,basement,Wizzard_Room;
	      
	        // create the rooms
	        marketsquare = new Room("on the market square");
	        templePyramid = new Room("in a temple pyramid");
	        tavern = new Room("in the tavern at the market square");
	        sacrificialSite = new Room("at a sacrificial site");
	        hut = new Room("in a hut");
	        jungle = new Room("in the jungle");
	        secretPassage = new Room("in a secret passage");
	        cave = new Room("in a cave");
	        beach = new Room("on the beach");
	        basement = new Room("in the basement");
	        Wizzard_Room = new Room("at the wizzards room");

	        // initialise room exits
	        //Directions (north,east,south,west)
	        //marketsquare.setExit(tavern, templePyramid, null, sacrificialSite,null,null);
	        marketsquare.setExit("north",tavern);
	        marketsquare.setExit("east", templePyramid);
	        marketsquare.setExit("north", tavern);
	        marketsquare.setExit("east", templePyramid);
	        marketsquare.setExit("west", secretPassage);
	       
	        templePyramid.setExit("north", hut);
	        templePyramid.setExit("west", marketsquare);
	        templePyramid.setExit("up", Wizzard_Room);
	        templePyramid.setExit("down", basement);
	        
	        tavern.setExit("south", marketsquare);
	        tavern.setExit("east", hut);
	       
	        sacrificialSite.setExit("east", marketsquare);
	        sacrificialSite.setExit("down", cave);
	        
	        hut.setExit("south", templePyramid);
	        hut.setExit("east", jungle);
	        hut.setExit("west", tavern);
	        
	        jungle.setExit("west", hut);
	        
	        secretPassage.setExit("east", cave);
	        secretPassage.setExit("west", cave);
	        
	        cave.setExit("south", beach);
	        cave.setExit("east", secretPassage);
	        cave.setExit("up", sacrificialSite);
	        
	        beach.setExit("north", cave);
	        Wizzard_Room.setExit("down", templePyramid);
	        basement.setExit("west", secretPassage);
	        basement.setExit("up", templePyramid);

	        currentRoom = marketsquare;  // start game on marketsquare
	    }

	    /**
	     *  Main play routine.  Loops until end of play.
	     */
	    public void play() 
	    {            
	        printWelcome();

	        // Enter the main command loop.  Here we repeatedly read commands and
	        // execute them until the game is over.
	                
	        boolean finished = false;
	        while (! finished) {
	            Command command = parser.getCommand();
	            finished = processCommand(command);
	        }
	        System.out.println("Thank you for playing.  Good bye.");
	    }

	    /**
	     * Print out the opening message for the player.
	     */
	    private void printWelcome() //!!!!!!!!!!!!!!Code_Duplizierung
	    {
	    	
	        System.out.println();
	        System.out.println("Welcome to the World of Zuul!");
	        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
	        System.out.println("Type 'help' if you need help.");
	        System.out.println();/*
	        System.out.println("You are " + currentRoom.getDescription());
	        System.out.print("Exits: ");*/
	    	//String Info = printRoomInformation();
	        printRoomInformation();
	    	

	    
	        
	    }

	    /**
	     * Given a command, process (that is: execute) the command.
	     * @param command The command to be processed.
	     * @return true If the command ends the game, false otherwise.
	     */
	    private boolean processCommand(Command command) 
	    {
	        boolean wantToQuit = false;

	        if(command.isUnknown()) {
	            System.out.println("I don't know what you mean...");
	            return false;
	        }

	        String commandWord = command.getCommandWord();
	        if (commandWord.equals("help")) {
	            printHelp();
	        }
	        else if (commandWord.equals("go")) {
	            goRoom(command);
	        }
	        else if (commandWord.equals("quit")) {
	            wantToQuit = quit(command);
	        }

	        return wantToQuit;
	    }

	    // implementations of user commands:

	    /**
	     * Print out some help information.
	     * Here we print some stupid, cryptic message and a list of the 
	     * command words.
	     */
	    private void printHelp() 
	    {
	        System.out.println("You are lost. You are alone. You wander");
	        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
	        System.out.println();
	        System.out.println("Your command words are:");
	        System.out.println("   go quit help");
	    }

	    /** 
	     * Try to go in one direction. If there is an exit, enter
	     * the new room, otherwise print an error message.
	     */
	    
	    
	    public void printRoomInformation() 
	    {

	    	System.out.println();
	    	System.out.println("You are " + currentRoom.getDescription());
	    	System.out.println("Exits: ");
	    	System.out.println(currentRoom.exitToString());
	    	System.out.println();
	    	
	    
	    }
	    
	    
	    private void goRoom(Command command) 
	    {
	        if(!command.hasSecondWord()) {
	            // if there is no second word, we don't know where to go...
	            System.out.println("Go where?");
	            return;
	        }
	        
	        //String Info = printRoomInformation();
	        
	        String direction = command.getSecondWord();

	        // Try to leave current room.
	        Room nextRoom = currentRoom.getExit(direction);
	        if(nextRoom==null) 
	        {
	        	//System.out.println("There is no door");
	        	System.out.println("There is no door");
	        }else {
	        	currentRoom = nextRoom;
	        	printRoomInformation();
	        }
	        
	    }

	    /** 
	     * "Quit" was entered. Check the rest of the command to see
	     * whether we really quit the game.
	     * @return true, if this command quits the game, false otherwise.
	     */
	    private boolean quit(Command command) 
	    {
	        if(command.hasSecondWord()) {
	            System.out.println("Quit what?");
	            return false;
	        }
	        else {
	            return true;  // signal that we want to quit
	        }
	    }
}
