package MyWorks.src.RelaxTime.Utils;

import MyWorks.src.RelaxTime.RelaxTime;
import MyWorks.src.RelaxTime.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Artem on 09.11.2015.
 */

public class PasswordUtil implements Util {
    private static ResourceBundle res = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "config_en");
    @Override
    public void execute() {
        if(allowAccessDate()) {
            ConsoleHelper.writeMessage(res.getString("password.request"));
            String prevPass = ConsoleHelper.readString();
            String correctPass = unCrypt(toBytes(prevPass));
            new LogStartUtil(correctPass).execute();
        } else {
            ConsoleHelper.writeMessage("Wrong request or time for a play session. Please, try again on Wed or Sun!");
        }
    }

    private boolean allowAccessDate()
    {
        String date = new Date().toString();
        ArrayList<String> dateArray = new ArrayList<>();
        String dateLine = date.replaceAll("[\\p{P}]", " ");
        String [] dateStr = dateLine.split(" ");
        Collections.addAll(dateArray, dateStr);
        return dateArray.contains("Tue") || dateArray.contains("Sun");
    }

    private byte [] toBytes(String prevPass)
    {
        char [] charArray = prevPass.toCharArray();
        byte [] byteArray = new byte[prevPass.length()];
        for (int i = 0; i < charArray.length; i++)
        {
            byteArray[i] = (byte) charArray[i];
        }
        return byteArray;
    }

    private String unCrypt(byte[] byteArray)
    {
        byte [] pass = new byte[byteArray.length];
        int key = Integer.parseInt(res.getString("key"));
        for (int i = 0; i < pass.length; i++)
        {
            pass[i] = (byte) (byteArray[i] ^ key);
        }

        StringBuilder res = new StringBuilder();
        for (byte b : pass) {
            res.append((char) b);
        }
        return res.toString();
    }
}
