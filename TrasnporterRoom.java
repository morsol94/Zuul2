

/**
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * text based adventure game.
 * 
 * The class ia a sub-class of the class Room and is used for rooms that have no fixed exits,
 * but randomize where you end up upon writing go "direction".
 * 
 * @author Morten
 * @version 17.03.2016 V0.1
 */
public class TrasnporterRoom extends Room
{
    public String description;
    private RoomRandomizer randomRooms;
    
    
    /**
     * Constructer for the TransportRoom class.
     * 
     * @param description of the room
     * @param randomRooms an object of the class RoomRandomizer
     */
    public TrasnporterRoom(String description, RoomRandomizer randomRooms)
    {
        super(description);
        this.randomRooms = randomRooms;
    }
    
    /**
     * overwrites the getExit methode in the super-class Room and returns a 
     * random room wich the player travels to.
     * 
     * @param direction ignored
     * @return a random room
     */
    @Override
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }
    
    /**
     * Returns a random room from the class RooomRandomizer.
     * 
     * @return a random room
     */
    private Room findRandomRoom()
    {
       Room newRoom = this.randomRooms.getRandomRoom();
        
        return newRoom;
    }
            
}
