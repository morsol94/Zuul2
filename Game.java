
import java.util.Stack;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Morten Solli
 * @version 2011.07.31
 */
public class Game
{

    private Player player;
    private Parser parser;
    private Room currentRoom;
    private RoomRandomizer randomRoom;

    
    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {

        randomRoom = new RoomRandomizer();
        createRooms();
        parser = new Parser();
        player = new Player("Kniven", currentRoom);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, basement, attic,
                dorm, myRoom, mattsRoom, commonRoom, batheroom, library, closet;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a chemestry lab");
        office = new Room("in the chemestry admin office");
        basement = new Room("in the basement of the university. It is dark,"
                + "\n you hear many wiered noises and you can swear something "
                + "\n touched your leg");
        attic = new Room("in the attic of the university, its a longe time"
                + "\n since someone have been here judging by the dust.");
        dorm = new Room("in the dorm where you and other students live");
        myRoom = new Room("in your room, this is where the magic happens,"
                + "\n also there is an open window you can jump out");
        mattsRoom = new Room("in mattsRoom. No magic here, and it"
                + "\n smells funny here");
        commonRoom = new Room("in the common area of your dorm,"
                + "\n there is a tv in the corner and some other"
                + "\n students here.");
        batheroom = new Room("in the batheroom of your dorm, it looks like"
                + "\n someone has used your towel");
        library = new Room("in the university library");
        closet = new TrasnporterRoom("This is a magical romm,we dont know where you will end up", randomRoom);
        
        
        //Adding rooms to the randoomRoom arraylist
        randomRoom.addRooms(outside);
        randomRoom.addRooms(theater);
        randomRoom.addRooms(pub);
        randomRoom.addRooms(lab);
        randomRoom.addRooms(office);
        randomRoom.addRooms(basement);
        randomRoom.addRooms(library);
        randomRoom.addRooms(myRoom);
        randomRoom.addRooms(dorm);
        randomRoom.addRooms(commonRoom);
        randomRoom.addRooms(batheroom);
        randomRoom.addRooms(attic);
        

        //initializing room exits for outside
        outside.setExit("north", dorm);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("east", theater);

        //initializing room exitas for lab
        lab.setExit("north", outside);
        lab.setExit("west", office);
        lab.setExit("down", basement);
        lab.setExit("up", attic);

        //initializing exits for office
        office.setExit("east", lab);
        office.setExit("north", closet);

        //initializing exits for attic
        attic.setExit("down", lab);

        //initialazing exits for basement
        basement.setExit("up", pub);

        //initialazing exits for pub
        pub.setExit("east", outside);

        //initialazing exits for theater
        theater.setExit("west", outside);
        theater.setExit("up", library);

        //initialazing exits for library
        library.setExit("down", theater);

        //initialazing exits for dorm
        dorm.setExit("north", myRoom);
        dorm.setExit("west", batheroom);
        dorm.setExit("east", commonRoom);
        dorm.setExit("south", outside);
        dorm.setExit("up", mattsRoom);

        //initializing exits for myRoom
        myRoom.setExit("north", outside);
        myRoom.setExit("south", dorm);

        //initializing exits for commonRoom
        commonRoom.setExit("west", dorm);

        //initialazing exits for myRoom
        mattsRoom.setExit("down", dorm);

        //Fill rooms with items
        //TODO: add items
        pub.addItem(new Item("beer", "A cold bottle of beer", 0.33, true, "you feel tipsy"));
        pub.addItem(new Item("ipa", "A nice bottle of IPA", 0.5, true, "you feel tipsy"));
        pub.addItem(new Item("glass", "A pint glass", 0.2, false, ""));
        pub.addItem(new Item("yngve", "a person named ynge he tells"
                + "you that you was here last night", 60, false, ""));

        outside.addItem(new Item("statue", "A tall statue of the first"
                + " principal", 500, false, ""));
        outside.addItem(new Item("fotball", "An real fotball, not an"
                + "wiered american fotball", 0.7, false, ""));

        lab.addItem(new Item("Meth", "A batch of crystall math", 0.5, true, ""
                + "You feel a tingeling scencation, your heart beats faster,"
                + "\nyou feel the rush, you can do anything!... atleast thats"
                + "what you belive"));
        lab.addItem(new Item("bottle", "Clean chemestry bottles", 0.3, false, ""));
        lab.addItem(new Item("coat", "A white lab coat", 1.0, false, ""));

        office.addItem(new Item("penn", "A golden penn", 0.4, false, ""));
        office.addItem(new Item("stappler", "A stappler machin", 0.7, false, ""));
        office.addItem(new Item("Ole", "Ole Martin", 80, false, ""));

        attic.addItem(new Item("mirror", "A dusty hand hell mirror", 1.5, false, ""));
        attic.addItem(new Item("omar", "A dusty person named Omar, "
                + "he tells you to go to the library and find The Book"
                + " of Thosend Throutes.", 90, false, ""));

        theater.addItem(new Item("Macbook", "A new macBook Pro standing"
                + " on the teacher's desk", 2, false, ""));
        theater.addItem(new Item("Bottle", "An empty water bottle", 0.15, false, ""));

        library.addItem(new Item("Java123", "Book named Java123", 7, false, ""));
        library.addItem(new Item("BlueJ", "Book named OOP with BlueJ", 2.5, false, ""));
        library.addItem(new Item("Cooking", "cook book named cooking", 4, false, ""));
        library.addItem(new Item("Electronics", "Book about electronics", 6, false, ""));
        library.addItem(new Item("BoTT", "BoTT short for Book of Thousend "
                + " Thruts", 5, false, ""));
        library.addItem(new Item("Selfies", "book of selfies", 3, false, ""));
        library.addItem(new Item("Meth123", "book about how to cook meth", 9, false, ""));
        library.addItem(new Item("Glasses", "forgotten flasses", 0.3, false, ""));

        dorm.addItem(new Item("Matt", "Matt greats you hello", 85, false, ""));

        myRoom.addItem(new Item("Wallet", "an empty wallet with the impression"
                + "of a condom in the skin", 0.25, false, ""));
        myRoom.addItem(new Item("Phone", "Phone with broken screen and 3 unseen"
                + " snap's on it", 0.35, false, ""));
        myRoom.addItem(new Item("Notebook", "Notebook with someting unreadable"
                + " scribbled inside", 0.4, false, ""));
        myRoom.addItem(new Item("Headphones", "a pair of old sennheiser's "
                + "headphones", 1.7, false, ""));
        myRoom.addItem(new Item("bread", "a loaf of bread", 0.5, true, "You are no"
                + " longer tipsy or high."));

        mattsRoom.addItem(new Item("joint", "A half smoken joint", 0.003, true, "you "
                + "feel relaxed, you no longer have any worries."));
        mattsRoom.addItem(new Item("Socks", "A pair of dirty socks", 0.7, false, ""));
        mattsRoom.addItem(new Item("Weights", "A 20Kg weight", 20, false, ""));
        mattsRoom.addItem(new Item("Photo", "A photo of Matt's mom", 0.7, false, ""));
        mattsRoom.addItem(new Item("cookie", "A magic cookie, what does it do?",
                0.004, true, "You feel happyer then you've been ever before. you "
                + "carrier cappacity has increased by 5Kg"));

        commonRoom.addItem(new Item("TV", "A flatscreen TV", 7.5, false, ""));

        batheroom.addItem(new Item("Toothbrush", "your toothbrush", 0.2, false, ""));
        batheroom.addItem(new Item("Toiletbrush", "A toilet brush", 0.8, false, ""));

        currentRoom = outside;  // start game outside
    }

    /**
     * Main play routine. Loops until end of play. ekstra kommentar
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        } else if (commandWord.equals("go"))
        {
            player.goRoom(command);
        } else if (commandWord.equals("back"))
        {
            player.goBack();
        } else if (commandWord.equals("look"))
        {
            player.look();
        } else if (commandWord.equals("consume"))
        {
            player.consume(command);
        } else if (commandWord.equals("take"))
        {
            player.takeItem(command);
        } else if (commandWord.equals("drop"))
        {
            player.dropItem(command);
        } else if (commandWord.equals("inventory"))
        {
            player.inventory();
        } else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(" " + parser.showCommands());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        } else
        {
            return true;  // signal that we want to quit
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();

    }

}
