package WallOfBricks.utils;

import WallOfBricks.ConsoleHelper;
import WallOfBricks.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Artem on 23.11.2015.
 */

public class ParseFile implements Command {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Please, enter the file path:");
        try {
            String fileName = ConsoleHelper.readString();
            BricksChecker fileDataChecker = new BricksChecker();
            HashMap<String, String> dataFile = getDataFromFile(fileName);
            fileDataChecker.processData(dataFile);              // Gets necessary data from file
            ConsoleHelper.writeMessage("Can you build a specified wall? The answer is:");
            if (!fileDataChecker.isEnoughBlocks())
                ConsoleHelper.writeMessage("No");               // Checks if we have enough blocks to build the wall
            else {
                fileDataChecker.placeMatchingBlocks();          // Places blocks which exactly match bricks in the wall
                if (fileDataChecker.getWallSizeInPixels() == 0) ConsoleHelper.writeMessage("Yes");
                else if (fileDataChecker.placeOneElement())
                    ConsoleHelper.writeMessage("Yes");          // Places the rest of blocks to build the wall
                else ConsoleHelper.writeMessage("No");          // Prints "No" in case we didn't finish the wall
            }
            ConsoleHelper.writeMessage("");
        } catch (InterruptOperationException ignored){
        } catch (Exception e) {
            System.out.println(e.toString() + " in ParseFile");
        }
    }

    /**Reads necessary data from file and stores it in HashMap
     * @return HashMap with all data from file: key - variable; value - data*/
    public HashMap<String, String> getDataFromFile(String fileName) throws IOException
    {
        HashMap<String, String> result = new HashMap<String, String>();
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String wallSize = reader.readLine().trim();
        String [] line = wallSize.split(" ");
        int height = 0;
        try {
            height = Integer.parseInt(line[1]);
        } catch (NumberFormatException e){
            System.out.println(e.toString());
        }
        result.put("wallSize", wallSize);

        String wallSpec;
        for (int i = 0; i < height; i++) {
            wallSpec = reader.readLine().trim();
            result.put("wallSpec" + i, wallSpec);
        }

        String countTypes = reader.readLine().trim();           // Reading the the quantity of brick types
        result.put("countTypes", countTypes);
        int count = Integer.parseInt(countTypes);
        while (count-- > 0)                                     // Reading the info about available bricks from the file
        {
            String brick = reader.readLine().trim();
            result.put("brick" + count, brick);
        }
        reader.close();
        return result;
    }
}