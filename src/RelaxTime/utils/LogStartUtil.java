package MyWorks.src.RelaxTime.Utils;

import MyWorks.src.RelaxTime.ConsoleHelper;
import MyWorks.src.RelaxTime.RelaxTime;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Artem on 09.11.2015.
 */

public class LogStartUtil implements Util {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static ResourceBundle res = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "config_en");
    private static ResourceBundle res2 = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "info_en");
    private String correctPass;
    public LogStartUtil(String s){
        this.correctPass=s;
    }

    @Override
    public void execute() {
        try (FileWriter writer = new FileWriter(res.getString("file"), true))
        {
            Date date = new Date();
            String todayStart = dateFormat.format(date) + "\r\n";
            String startTime = String.format("%19s", System.currentTimeMillis());
            writer.append("Game session started: " + todayStart + "   Password: " + correctPass + " " + startTime +"\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }
        ConsoleHelper.writeMessage("Your password is: "+correctPass);
        ConsoleHelper.writeMessage(res2.getString("Success.START")+"\n");
    }
}
