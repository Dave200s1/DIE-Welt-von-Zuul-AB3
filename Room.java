import java.util.HashMap;
import java.util.Map;

public class Room {

	private String description;
	//private Room northExit;
	//private Room southExit;
	//private Room eastExit;
	//private Room westExit;
	//private Room upExit;
	//private Room downExit;
	private HashMap<String,Room>exits = new HashMap<>();
	
	
	
	
	 /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    public Room getExit(String direction) 
    {
    	//direction = "";
    	
    	return this.exits.get(direction);
    }
    
    public Room exitToString() 
    {
    	StringBuilder exits = new StringBuilder();

        
    	for(String direction:this.exits.keySet()) 
    	{
    		exits.append(direction).append("");
    	}
    
    	
    	//this.description = exit.toString();
    	//System.out.println(description);
    	System.out.println(exits);
    	return null;
    }
     	
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction,Room neighbour) 
    {
    	this.exits.put(direction,neighbour);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
}
