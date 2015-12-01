package WallOfBricks.utils;

import WallOfBricks.ConsoleHelper;

/**
 * Created by Artem on 23.11.2015.
 */

public class ExitCommand implements Command {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Thank you for using WallOfBricks app! Have a good day!");
    }
}
