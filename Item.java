
/**
 * Class Item - an item in a room in an adventure game
 * 
 * This class is ppart of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 *  "Item" represent one item in the scenery of a room in the game.
 *  The Item class holdes a description, weight in Kg, and a name to
 *  the item in private fields. Item's can be picked up and put in the
 *  players backpack.
 * 
 * @author Morten Solli
 * @version 25.02.2016 V1.0
 */
public class Item
{
    private String name;    //One word name.
    private String description;  //Longer description of the object.
    private double weigth;  //weigth of the item in Kg.
    
    
    /**
     * Creates an item with a name ex. "bottle", a description
     * ex. "a cold bottle of beer", and a weight in Kg ex. "0,5Kg".
     * 
     * @param name The name of the object 
     * @param description The description of the object
     * @param weigth  The weight of the object in Kg.
     */
    public Item(String name, String description, double weigth)
    {
        this.name = name;
        this.description = description;
        this.weigth = weigth;
    }
    
    
    /**
     * Returns the name of the item
     * 
     * @return The name of the item 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns the description of the item
     * 
     * @return The description of the item 
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Returns the weight of the item in Kg.
     * 
     * @return The weight of the object in Kg.
     */
    public double getWeigth()
    {
        return this.weigth;
    }
    
    /**
     * Returns the full details of the item; Name, Description,
     * and Weigth in KG.
     * 
     * @return The Name, Description, and weight in Kg.
     */
    public String getItemDetails()
    {
        return this.getName()+ " - " + this.getDescription()+ " - " + this.getWeigth() +"Kg";
    }
}
