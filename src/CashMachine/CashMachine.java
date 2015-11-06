package CashMachine;

import CashMachine.command.CommandExecutor;
import CashMachine.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Artem on 04.11.2015.
 */

public class CashMachine {
    public static final String RESOURCE_PATH = "CashMachine.resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){
            try {
                CommandExecutor.execute(Operation.EXIT);
            } catch (InterruptOperationException ignored) {
            }
        }
    }
}
