
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.eat
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Morten Solli
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ItemCollection items;
    private Item item;


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
        items = new ItemCollection();
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
        String longDsc = "You are ";    
              longDsc += this.description;
              longDsc += ".\n" + getExitStrings();
                
        if(0 == items.getNumberOfitems())
        {
            longDsc += "\nThere is no items in this room.";
        }
        else
        {
            longDsc +="\nitem: ";
            Iterator<Item> it = items.getAllItems();
            while(it.hasNext())
            {
                Item item = it.next();
                longDsc += "\n" + item.getItemDetails() + " ";
            }
        }
        return longDsc;
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
        Iterator<String> dirIt = exits.keySet().iterator();
        while(dirIt.hasNext())
        {            
            returnString += dirIt.next() + " ";
        }
        
        return returnString;
    }
    
    /**
     * Adds an item to the item collection to a spesific room 
     *
     * @param item 
     */
    public void addItem(Item item)
    {        
       items.putItem(item);
    }
    
    /**
     * Gets the item from the item collection and is used to add it to the
     * player
     * 
     * @param keyWord
     * @return 
     */
    public Item getItem(String keyWord)
    {
        return this.items.getItem(keyWord);
    }
    
    
    /**
     * Removes a spesific item from a the item collection to the
     * room you are in
     * 
     * @param keyWord 
     */
    public void removeItem(String keyWord)
    {
        items.removeItem(keyWord);
    }
    
    /**
     * Gets the weigt of a spesific item in a specfic item colection
     * in kg.
     * 
     * @param item
     * @return Weigth of the item in KG
     */
    public double getItemWeigth(Item item)
    {
        return items.getItemWeigth(item);
    }
    
    
    protected HashMap getExits()
    {
        return exits;
    }
    
    
            
          
           
    
}
