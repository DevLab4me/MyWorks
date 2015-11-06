package CashMachine.command;

import CashMachine.ConsoleHelper;
import CashMachine.CurrencyManipulator;
import CashMachine.CurrencyManipulatorFactory;
import CashMachine.exception.InterruptOperationException;
import CashMachine.exception.NotEnoughMoneyException;

/**
 * Created by Artem on 04.11.2015.
 */

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Please, enter the currency:");
        String currency = ConsoleHelper.askCurrencyCode();
        int amount;
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        while(true){
            ConsoleHelper.writeMessage("Enter the amount:");
            String am = ConsoleHelper.readString();
            try {
                amount = Integer.parseInt(am);
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage("Please, input a correct data");
                continue;
            }
            if(amount <= 0){
                ConsoleHelper.writeMessage("Please, input a correct data");
                continue;
            }
            if(!manipulator.isAmountAvailable(amount)){
                ConsoleHelper.writeMessage("Not enough money");
                continue;
            }
            try {
                manipulator.withdrawAmount(amount);
            }catch (NotEnoughMoneyException e){
                ConsoleHelper.writeMessage("Not enough money");
            }
            break;
        }
    }
}
