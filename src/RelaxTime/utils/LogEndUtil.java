package MyWorks.src.RelaxTime.Utils;

import MyWorks.src.RelaxTime.ConsoleHelper;
import MyWorks.src.RelaxTime.RelaxTime;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Artem on 09.11.2015.
 */

public class LogEndUtil implements Util {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static ResourceBundle res = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "config_en");
    private static ResourceBundle res2 = ResourceBundle.getBundle(RelaxTime.RESOURCE_PATH + "info_en");
    private String fileName = res.getString("file");

    @Override
    public void execute() {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            Date date = new Date();
            String todayEnd = dateFormat.format(date) + "\r\n";
            Long endTime = System.currentTimeMillis();
            String timeSpent = countTimeSpentOnPlaying(endTime);
            if(!timeSpent.equals("")) {
                writer.append("Game session ended:   " + todayEnd);
                deleteLastLine(fileName);
                writer.append("Time spent on playing: " + timeSpent + "\r\n");
                writer.append("---   ---   ---   ---   ---" + "\r\n");
                ConsoleHelper.writeMessage(res2.getString("Success.END"));
            }
        }catch (IOException ignored) {
        }
    }

    private String countTimeSpentOnPlaying(Long endTime)
    {
        String resTime="";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw"))
        {
            long fileSize = randomAccessFile.length();
            randomAccessFile.seek(fileSize - 20);
            StringBuilder stringBuilder = new StringBuilder();

            int oneByte;
            while ((oneByte = randomAccessFile.read()) != -1)
            {
                stringBuilder.append((char) oneByte);
            }
            String timeFromFile = stringBuilder.toString().trim();

            try
            {
                long logTime = Long.parseLong(timeFromFile);
                long timeSpent = endTime - logTime;

                long second = (timeSpent / 1000) % 60;
                long minute = (timeSpent / (1000 * 60)) % 60;
                long hour = (timeSpent / (1000 * 60 * 60)) % 24;

                if (hour < 1)
                    resTime = String.format("%02d mins : %02d secs", minute, second);
                else
                    resTime = String.format("%02dh : %02dmins", hour, minute);
            }
            catch (NumberFormatException e)
            {
                System.out.println(res2.getString("Session.not.started.yet"));
            }
        } catch (IOException ignored)
        {
        }
        return resTime;
    }

    private static void deleteLastLine(String fileName)
    {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw"))
        {
            long length = randomAccessFile.length() - 1;
            byte b;
            do
            {
                length -= 1;
                randomAccessFile.seek(length);
                b = randomAccessFile.readByte();
            } while (b != 10);
            randomAccessFile.setLength(length + 1);
        } catch (IOException e) {
            System.out.println("Smth wrong in method -> 'deleteLastLine'");
        }
    }
}
