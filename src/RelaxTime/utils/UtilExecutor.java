package MyWorks.src.RelaxTime.Utils;

import MyWorks.src.RelaxTime.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 09.11.2015.
 */

public class UtilExecutor {
    private static Map<Operation, Util> commandMap = new HashMap<>();
    private UtilExecutor(){}

    static {
        commandMap.put(Operation.PASSWORD, new PasswordUtil());
        commandMap.put(Operation.END, new LogEndUtil());
        commandMap.put(Operation.EXIT, new ExitUtil());
    }

    public static void execute(Operation operation) {
        commandMap.get(operation).execute();
    }
}

