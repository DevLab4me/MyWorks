package MyWorks.src.RelaxTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Artem on 09.11.2015.
 */

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "info_en");

    public static String readString() {
        String message = "";
        try{
            message = reader.readLine();
        } catch (IOException ignored){}
        return message;
    }

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static Operation askOperation() {
        Operation operation;
        while(true){
            writeMessage(res.getString("choose.operation") + "\n" +
                    "1 - " + res.getString("operation.START") + "\n" + "2 - " + res.getString("operation.END") +
                    "\n" + "3 - " + res.getString("operation.EXIT"));
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
}
