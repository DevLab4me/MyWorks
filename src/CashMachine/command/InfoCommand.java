package CashMachine.command;


import CashMachine.CashMachine;
import CashMachine.ConsoleHelper;
import CashMachine.CurrencyManipulator;
import CashMachine.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

/**
 * Created by Artem on 04.11.2015.
 */

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        boolean money = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        for (CurrencyManipulator current : CurrencyManipulatorFactory.getAllCurrencyManipulators()){
            if(current.hasMoney()) {
                if(current.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(current.getCurrencyCode() + " - " + current.getTotalAmount());
                    money = true;
                }
            }
        }
        if(!money)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
