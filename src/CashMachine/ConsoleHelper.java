package CashMachine;

import CashMachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Artem on 04.11.2015.
 */

public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString()throws InterruptOperationException {
        String message = "";
        try{
            message = reader.readLine();
            if(message.equalsIgnoreCase(Operation.EXIT.toString())){
                throw new InterruptOperationException();
            }
        } catch (IOException ignored){

        }
        return message;
    }
    public static String askCurrencyCode()throws InterruptOperationException{
        String test;
        String validCurrencyName;
        while(true){
            writeMessage(res.getString("choose.currency.code"));
            test = readString();
            if(test.length()==3){
                validCurrencyName = test.toUpperCase();
                break;
            } else
                writeMessage(res.getString("invalid.data"));
        }

        return validCurrencyName;
    }

    public static String[] getValidTwoDigits(String currencyCode)throws InterruptOperationException{
        String[] array;

        while(true){
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            array = readString().split(" ");
            int k, l;
            try{
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }catch (Exception e){
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2){
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }
    public static Operation askOperation()throws InterruptOperationException{
        Operation operation;
        while(true){
            writeMessage(res.getString("choose.operation") + "\n" +
                    "1 - " + res.getString("operation.INFO") + "\n" + "2 - " + res.getString("operation.DEPOSIT") + "\n" +
                    "3 - " + res.getString("operation.WITHDRAW") + "\n" + "4 - " + res.getString("operation.EXIT"));
            String answer = readString();
            try {
                int choice = Integer.parseInt(answer);
                operation = Operation.getAllowableOperationByOrdinal(choice);
                break;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
