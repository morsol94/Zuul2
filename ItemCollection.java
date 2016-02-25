
import java.util.HashMap;
 
/**
 * Class Items - an collection of items.
 * 
 *  This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * @author Morten Solli
 * @version 25.02.2016 V1.0
 */
public class ItemCollection
{
    private HashMap<String, Item> itemCollection;
    
    
    /**
     * Constructer of the Items class
     */
    public ItemCollection()
    {
        itemCollection = new HashMap<String, Item>();
    }
    
    /**
     * Place an item into the collection
     * 
     * @param item 
     */
    public void putItem( Item item )
    {
        itemCollection.put(item.getName(), item);
    }
    
    
    public double getTotalWeigth()
    {
        double totalWeigth = 0;
        for (Item item : itemCollection.values())
        {
          totalWeigth += item.getWeigth();
        }
        
        return totalWeigth;
    }
}
