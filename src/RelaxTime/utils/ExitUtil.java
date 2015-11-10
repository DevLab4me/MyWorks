package MyWorks.src.RelaxTime.Utils;

import MyWorks.src.RelaxTime.ConsoleHelper;
import MyWorks.src.RelaxTime.RelaxTime;

import java.util.ResourceBundle;

/**
 * Created by Artem on 09.11.2015.
 */

public class ExitUtil implements Util {
    private static ResourceBundle res = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("Thanks.Bye"));
    }
}
