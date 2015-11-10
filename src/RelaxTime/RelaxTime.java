package MyWorks.src.RelaxTime;

import MyWorks.src.RelaxTime.Utils.UtilExecutor;

import java.util.Locale;


/**
 * Created by Artem on 09.11.2015.
 */

public class RelaxTime {
    public static final String RESOURCE_PATH = "MyWorks.src.RelaxTime.resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        ConsoleHelper.writeMessage("Greetings to RelaxTime application!");
        Operation operation;
        do {
            operation = ConsoleHelper.askOperation();
            UtilExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}
