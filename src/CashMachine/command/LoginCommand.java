package CashMachine.command;

import CashMachine.CashMachine;
import CashMachine.ConsoleHelper;
import CashMachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Artem on 06.11.2015.
 */

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        while(true){
            String cardNum = ConsoleHelper.readString();
            if(validCreditCards.containsKey(cardNum)) {
                String pinCode = ConsoleHelper.readString();
                if (pinCode.equals(validCreditCards.getString(cardNum))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNum));
                    break;
                }else{
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNum));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
