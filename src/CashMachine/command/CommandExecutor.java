package CashMachine.command;

import CashMachine.Operation;
import CashMachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 04.11.2015.
 */

public class CommandExecutor {
    private static Map<Operation, Command> commandMap = new HashMap<>();
    private CommandExecutor(){}

    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        commandMap.get(operation).execute();
    }
}
