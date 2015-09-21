package src.com.MyWorks;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RelaxTime
{
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static String fileName = "D:\\Games\\PassRequestLog.txt";
    private static final int key = 10;

    public static void main(String[] args)
    {
        String request = args[0];
        String password = args[1];
        byte [] byteArray = toBytes(password);
        String date = new Date().toString();
        boolean allow = allowAccessDate(date);

        if (request.equals("-p") && allow)
        {
            byte[] correctPass = unCrypt(byteArray);
            String newPassword = fromBytes(correctPass);
            System.out.println(newPassword);
            System.out.println("Game session started!");
            logGameStart(newPassword);
        } else if (request.equals("-e"))
        {
            System.out.println("Game session ended!");
            logGameEnd();
        } else
        {
            System.out.println("Wrong request or time for a play session. Please, try again on Wed or Sun!");
            return;
        }
    }
    private static boolean allowAccessDate(String date)
    {
        ArrayList<String> dateArray = new ArrayList<>();
        String dateLine = date.replaceAll("[\\p{P}]", " ");
        String [] dateStr = dateLine.split(" ");
        Collections.addAll(dateArray, dateStr);
        return dateArray.contains("Wed") || dateArray.contains("Sun");
    }

    private static byte [] toBytes(String password)
    {
        char [] charArray = password.toCharArray();
        byte [] byteArray = new byte[password.length()];
        for (int i = 0; i < charArray.length; i++)
        {
            byteArray[i] = (byte) charArray[i];
        }
        return byteArray;
    }

    private static String fromBytes(byte[] byteArray)
    {
        StringBuilder res = new StringBuilder();
        for (byte b : byteArray) {
            res.append((char) b);
        }
        return res.toString();
    }

    private static byte [] unCrypt(byte [] pass)
    {
        byte [] res = new byte[pass.length];
        for (int i = 0; i < pass.length; i++)
        {
            res[i] = (byte) (pass[i] ^ key);
        }
        return res;
    }

    private static void logGameStart(String correctPass)
    {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            Date date = new Date();
            String todayStart = dateFormat.format(date) + "\r\n";
            String startTime = String.format("%19s", System.currentTimeMillis());
            writer.append("Game session started: " + todayStart + "   Password: " + correctPass + " " + startTime +"\r\n");
        }catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    private static void logGameEnd()
    {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            Date date = new Date();
            String todayEnd = dateFormat.format(date) + "\r\n";
            Long endTime = System.currentTimeMillis();
            writer.append("Game session ended:   " + todayEnd);
            String timeSpent = countTimeSpentOnPlaying(endTime);
            deleteLastLine(fileName);
            writer.append("Time spent for playing: " + timeSpent + "\r\n");
            writer.append("---   ---   ---   ---   ---" + "\r\n");
        }catch (IOException e) {
            System.out.println("IOException: " + e);
        }
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
        }
        while (b != 10);
        randomAccessFile.setLength(length + 1);
        }catch (IOException e) {
        System.out.println("Smth wrong in method -> 'deleteLastLine'");
        }
    }

    private static String countTimeSpentOnPlaying(Long endTime)
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
            System.out.println(timeFromFile);
            try
            {
                long logTime = Long.parseLong(timeFromFile);
                long timeSpent = endTime - logTime;

                long second = (timeSpent / 1000) % 60;
		long minute = (timeSpent / (1000 * 60)) % 60;
		long hour = (timeSpent / (1000 * 60 * 60)) % 24-17;
				
		if (hour<1)
			resTime = String.format("%02d mins : %02d secs", minute, second);
                else
                	resTime = String.format("%02dh : %02dmins", hour, minute);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Couldn't parse String:" + timeFromFile + ":NumberFormatException");
            }
        }catch (IOException e)
        {
            System.out.println("Smth wrong in method -> 'countTimeSpentForPlaying'");
        }
        return resTime;
    }
}
