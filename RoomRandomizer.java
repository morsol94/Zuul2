

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * text based adventure game.
 * 
 * This class is used to generate an random room from the arraylist "rooms" and return 
 * it to the TransporterRoom class.
 * 
 * @author Morten
 * @version 17.03.2016 V0.1
 */
public class RoomRandomizer
{
    private ArrayList<Room> rooms;       //collection of rooms wich is avilebel for random travel.
    private Random randomGenerator;      //used as a random number generator.
    
    
    /**
     * Constructer for the RoomRandomizer class
     * cresta a new instance of ArrayList and Random.
     */
    public RoomRandomizer()
    {
        rooms = new ArrayList();
        randomGenerator = new Random();
    }
    
    /**
     * Add rooms to the rooms arraylist.
     * 
     * @param room 
     */
    public void addRooms(Room room)
    {
        rooms.add(room);
    }
    
    /**
     * Returns a random room with use of the of the random number generatet by the 
     * randomGenerator methode.
     * 
     * @return a random room
     */
    public Room getRandomRoom()
    {
        Room room = rooms.get(getRandomNumber());
        
        return room;
    }
    
    /**
     * Returns a random number within the size of the arraylist.
     * 
     * @return a random number  
     */
    private int getRandomNumber()
    {
       return randomGenerator.nextInt(rooms.size());
    }
}
