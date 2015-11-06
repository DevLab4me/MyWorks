package CashMachine.command;

import CashMachine.CashMachine;
import CashMachine.ConsoleHelper;
import CashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Artem on 04.11.2015.
 */

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (answer.equalsIgnoreCase(res.getString("yes")))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
