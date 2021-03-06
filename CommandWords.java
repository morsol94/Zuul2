
/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Morten Solli
 * @version 2011.07.31
 */

public class CommandWords
{

    // a constant array that holds all valid command words
    private static final String[] validCommands =
    {
        "go", "quit", "help", "look", "consume", "back", "take", "drop", "inventory"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for (int i = 0; i < validCommands.length; i++)
        {
            if (validCommands[i].equals(aString))
            {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Returns all the valid command words in the game.
     *
     * @return String with all command words
     */
    public String getCommandList()
    {

        String commands = " ";

        for (String command : validCommands)
        {
            commands += " " + command;
        }

        return commands;
    }
}
