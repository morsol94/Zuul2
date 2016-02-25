
import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item = null;
    private HashMap<String, Item> items = null;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    /**
     * Sotres information about wich rooms lies next to the
     * room you are inn.
     * 
     * @param direction
     * @param neighbor 
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return a long description of this room, of the form:
     *        You are in the kitchen.
     *        Exits: north wesst
     * 
     * @return A description of hte room, including exits.
     */
    public String getLongeDescription()
    {
        return "You are " + description 
               + ".\n" + getExitStrings()
               + "\n"  + item.getItemDetails();
     
    }
    
    /**
     * Creates exits for each room and binds each exit to 
     * its own direction.
     * 
     * @param direction
     * @return 
     */
    public Room getExit(String direction)
    {
       return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitStrings()
    {

        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        
        return returnString;
    }
    
    
    public void addItem(Item item)
    {
        this.item = item;
    }

}
