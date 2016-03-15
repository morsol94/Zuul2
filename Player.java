
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * text based adventure game.
 *
 * This class holds information about the player. The informationt the player
 * has is what room he is in, how much weigth he can carry in Kg, etc.....
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
     * Creates the player and starts the game. Also takes in the name of the
     * player.
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
     * Uses an stack class to store the rooms the player have visited, and when
     * using the back function it returns the player to the previous room and
     * delete it from the stack and moves the room stored under to the top.
     */
    public void goBack()
    {
        if (rommsVissited.empty())
        {
            System.out.println("You are at the start point");
        } else
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
    public void consume(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("consume what?");
            return;
        }

        Item edibleItem = this.playerCurrentRoom.getItem(command.getSecondWord());

        if (edibleItem.isEdible() == false)
        {
            System.out.println("\nYou can not eat this item");
        } else
        {
            System.out.println("You have consumed " + edibleItem.getName()
                    + ". " + edibleItem.getEatEffect());
            this.playerCurrentRoom.removeItem(command.getSecondWord());

            if (edibleItem.getName().equals("cookie"))
            {
                this.weigthLimit += 5;
            }

        }
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
     * Makes you look around the room and prints the exits and items you see.
     */
    public void look()
    {
        printLocationInfo();
    }

    /**
     * Replace with goBack
     *
     * Takes the player back to the previous room he was in and set the room he
     * was in before he wrote back to previous room.
     */
    public void goOneBack()
    {

        Room nextPreviousRoom = this.playerCurrentRoom;
        if (this.playerPreviousRoom != null && this.playerPreviousRoom != this.playerCurrentRoom)
        {
            this.playerCurrentRoom = this.playerPreviousRoom;
            printLocationInfo();
        } else
        {
            System.out.println("You have not been in any room before this one");
        }

        this.playerPreviousRoom = nextPreviousRoom;

    }

    /**
     * Makes it possible for the player to pick up an item from the room he is
     * current in and put it inside his backpack, as long as the combined weigth
     * of the backpack is under the weigthlimit.
     *
     * @param command : The item wich the player wants to take.
     */
    public void takeItem(Command command)
    {

        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("pick up what?");
            return;
        }

        Item selectedItem = this.playerCurrentRoom.getItem(command.getSecondWord());

        if (null == selectedItem)
        {
            System.out.println("no item by that name exist in this room");
        } else
        {
            double backPackWeigth = getTotalWeigthOfBackpack();
            double itemWeigth = playerCurrentRoom.getItemWeigth(selectedItem);

            if (backPackWeigth + itemWeigth <= weigthLimit)
            {
                backPack.put(selectedItem.getName(), selectedItem);
                this.playerCurrentRoom.removeItem(command.getSecondWord());

                System.out.println("You have picked up " + command.getSecondWord());
            } else
            {
                System.out.println("You dont have the capacity to carry this much "
                        + "weigth, drop something from your backPack if "
                        + "you want to carry this item");
            }

        }
    }

    /**
     * MAkes it possible for the player to take something out of his backpack
     * and put it in the room the player is current inside.
     *
     * @param command : The item that the player wants to drop
     */
    public void dropItem(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("drop what?");
            return;
        }

        Item selectedItem = this.backPack.get(command.getSecondWord());

        if (null == selectedItem)
        {
            System.out.println("There is no item by that name in your backpack");
        } else
        {
            this.playerCurrentRoom.addItem(selectedItem);
            this.backPack.remove(command.getSecondWord());

            System.out.println("You have droped " + command.getSecondWord());
        }

    }

    /**
     * Gets the total weight of the items in the backpack, the information about
     * each of them and returns it as a string.
     *
     * @return : Weight of items in the backpack and info about easach.
     */
    public void inventory()
    {
        double totalWeight = this.getTotalWeigthOfBackpack();

        String itemsInBackpack = this.getLongeDescription();

        String inventoryInfo = "\nTotal weigth of the items in your backpak is "
                + totalWeight + "Kg."
                + "\nYou are carrying: " + itemsInBackpack;

        System.out.println(inventoryInfo);

    }

    /**
     * goes trough each item in the backpack and adds the weight of each item
     *
     * @return : The combined weight of all the items in the backpack in Kg
     */
    private double getTotalWeigthOfBackpack()
    {
        double totalWeigth = 0;

        for (Item item : backPack.values())
        {
            totalWeigth += item.getWeigth();
        }

        return totalWeigth;
    }

    /**
     * Return a detailed description of what is in the players backpack. name of
     * the item, item description, and weight of the item.
     *
     * @return A description of items in the players backpack
     */
    public String getLongeDescription()
    {
        String backPackDsc = "";

        if (0 == this.getNumberOfitems())
        {
            backPackDsc += "\nYou have no items in your backpack.";
        } else
        {
            backPackDsc += "";
            Iterator<Item> it = this.getAllItems();
            while (it.hasNext())
            {
                Item item = it.next();
                backPackDsc += "\n" + item.getItemDetails() + " ";
            }
        }
        return backPackDsc;
    }

    /**
     * Returns the values to all the items in the collection.
     *
     * @return value
     */
    public Iterator<Item> getAllItems()
    {
        return this.backPack.values().iterator();

    }

    /**
     * Returns the number of Items in the collection
     *
     * @return number of items.
     */
    public int getNumberOfitems()
    {
        return backPack.size();
    }

}
