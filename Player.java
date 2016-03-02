
import java.util.HashMap;
import java.util.Stack;


/**
 *This class is part of the "World of Zuul" application.
 * "World of Zuul" is a text based adventure game.
 * 
 * This class holds information about the player.
 * The informationt the player has is what room he is in, how much
 * weigth he can carry in Kg, etc.....
 * 
 * @author Morten
 */
public class Player
{
    private String playerName;
    private double weigthLimit = 25.00; //Weigthlimit in Kg
    private Room playerCurrentRoom;
    private Room playerPreviousRoom;
    private Stack<Room> rommsVissited;
    private HashMap<String, Item> backPack;
    
    /**
     * Creates the player and starts the game. Also takes in the name
     * of the player.
     * 
     * @param name The players name
     */
    public Player(String name, Room currentRoom)
    {     
        this.playerName = name;
        this.rommsVissited = new Stack<Room>();
        this.playerCurrentRoom = currentRoom;
        this.backPack = new HashMap<>();
    }
    
      /**
     * Uses an stack class to store the rooms the player have visited, 
     * and when using the back function it returns the player to the
     * previous room and delete it from the stack and moves the room
     * stored under to the top.
     */
    public void goBack()
    {
        if (rommsVissited.empty())
        {
            System.out.println("You are at the start point");
        }
        else 
        {
            this.playerCurrentRoom = rommsVissited.pop();
            printLocationInfo();
        }
        
    }
    
        /**
     * prints out the getLongDescription methode from the Room class.
     */
    public void printLocationInfo()
    {
        System.out.println(this.playerCurrentRoom.getLongeDescription());
    }
    
    
        /**
     * Makes the player eat.
     */
    public void eat()
    {
        System.out.println("You have now feasted, you are no "
                + "\n longer hungry and ready to wander about freely");
        System.out.println();
    }
    
    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    public void goRoom(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = this.playerCurrentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        } else
        {
            rommsVissited.push(this.playerCurrentRoom);
            this.playerPreviousRoom = this.playerCurrentRoom;
            this.playerCurrentRoom = nextRoom;
            printLocationInfo();
            
        }
    }
    
    /**
     * Makes you look around the room and prints the exits 
     * and items you see.
     */
    public void look()
    {
        printLocationInfo();
    }
    
    
     /**
      * Replace with goBack
      * 
      * Takes the player back to the previous room he was in and set the
      * room he was in before he wrote back to previous room.
      */
      public void goOneBack()
      {
    
         Room nextPreviousRoom = this.playerCurrentRoom;
         if (this.playerPreviousRoom != null && this.playerPreviousRoom != this.playerCurrentRoom)
         {
         this.playerCurrentRoom = this.playerPreviousRoom;        
            printLocationInfo();
         }
         else
         {
            System.out.println("You have not been in any room before this one");
         }
        
         this.playerPreviousRoom = nextPreviousRoom;

     }
      
    public void takeItem(Command command)
    {
        
         if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("pick up what?");
            return;
        }
                 
        
         Item selectedItem = this.playerCurrentRoom.getItem(command.getSecondWord());
        
//        if (null == selectedItem)
//        {
//            System.out.println("no item by that name exist in this room");
//        }
//        else
//        {         
            backPack.put(selectedItem.getName(), selectedItem);
            this.playerCurrentRoom.removeItem(command.getSecondWord());
            
            System.out.println("You have picked up " + command.getSecondWord());
//        }
    }
    
    
    public void dropItem()
    {
        
    }
    

    
}
