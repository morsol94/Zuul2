
import java.util.HashMap;
import java.util.Iterator;
 
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
        itemCollection = new HashMap<>();
        
    }
    
    /**
     * Place an item into the collection
     * 
     * @param item 
     */
//    public void putItem( Item item )
//    {
//        itemCollection.put(item.getName(), item);
//    }
    
    
    public double getTotalWeigth()
    {
        double totalWeigth = 0;
        for (Item item : itemCollection.values())
        {
          totalWeigth += item.getWeigth();
        }
        
        return totalWeigth;
    }
    
    /**
     * Returns the values to all the items in the collection.
     * 
     * @return value 
     */
    public Iterator<Item> getAllItems()
    {
        return this.itemCollection.values().iterator();
        
    }
    
    /**
     * Adding an item to the itemCollection.
     * 
     * @param item: of the class Item 
     */
    public void putItem(Item item)
    {
        itemCollection.put(item.getName(), item);
    }
    
    /**
     * Returns the number of Items in the collection
     * 
     * @return number of items. 
     */
    public int getNumberOfitems()
    {
        return itemCollection.size();
    }
    
    
}
