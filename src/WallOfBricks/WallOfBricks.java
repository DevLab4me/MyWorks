package WallOfBricks;

import WallOfBricks.exception.InterruptOperationException;
import WallOfBricks.utils.CommandExecutor;

/**
 * Created by Artem on 23.11.2015.
 */

public class WallOfBricks {
    public static void main(String[] args){
        ConsoleHelper.writeMessage("Greetings to WallOfBricks application!");
        try {
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){
            CommandExecutor.execute(Operation.EXIT);
        }
    }
}
