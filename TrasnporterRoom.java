
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Morten
 */
public class TrasnporterRoom extends Room
{
    public String description;

    /**
     * 
     * 
     * @param description 
     */
    public TrasnporterRoom(String description)
    {
        super(description);
    }
    
    
    @Override
    public Room getExit(String direction)
    {
        
    }
    
    
    
}