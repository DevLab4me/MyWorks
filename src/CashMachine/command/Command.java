package CashMachine.command;


import CashMachine.exception.InterruptOperationException;

/**
 * Created by Artem on 04.11.2015.
 */

interface Command {
    void execute() throws InterruptOperationException;
}
