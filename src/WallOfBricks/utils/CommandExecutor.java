package WallOfBricks.utils;

import WallOfBricks.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 23.11.2015.
 */

public class CommandExecutor {
    private static Map<Operation, Command> commandMap = new HashMap<Operation, Command>();
    private CommandExecutor(){}

    static {
        commandMap.put(Operation.PARSE_FILE, new ParseFile());
        commandMap.put(Operation.PARSE_INPUT, new ParseInputLine());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    public static void execute(Operation operation) {
        commandMap.get(operation).execute();
    }
}
