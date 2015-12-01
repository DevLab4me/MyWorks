package WallOfBricks.utils;

import WallOfBricks.ConsoleHelper;
import WallOfBricks.exception.InterruptOperationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Artem on 23.11.2015.
 */

public class ParseInputLine implements Command {
    @Override
    public void execute() {
        try {
            HashMap<String, String> data = new HashMap<String, String>();         // Reading from input and saving data into a map
            int[] wallSize = ConsoleHelper.askWallSize();
            int width = wallSize[0], height = wallSize[1];
            data.put("wallSize", width + " " + height);              // Storing wallSize into a map
            ArrayList<String> wallSpecs = ConsoleHelper.askWallSpecs(width, height);
            for (int i = 0; i < wallSpecs.size(); i++) {
                data.put("wallSpec" + i, wallSpecs.get(i));          // Storing wallSpec into a map
            }
            int countTypes = ConsoleHelper.askBrickTypeQuantity();
            data.put("countTypes", String.valueOf(countTypes));      // Storing count of brick types into HashMap
            ArrayList<String> bricks = ConsoleHelper.askBrickSpecs(countTypes);
            for (int i = 0; i < bricks.size(); i++) {
                data.put("brick" + i, bricks.get(i));                // Storing bricks into a map
            }

            BricksChecker inputChecker = new BricksChecker();
            inputChecker.processData(data);                      // Gets necessary data from file
            ConsoleHelper.writeMessage("Can you build a specified wall? The answer is:");
            if (!inputChecker.isEnoughBlocks())
                ConsoleHelper.writeMessage("No");                // Checks if we have enough blocks to build the wall
            else {
                inputChecker.placeMatchingBlocks();              // Places blocks which exactly match bricks in the wall
                if (inputChecker.getWallSizeInPixels() == 0) ConsoleHelper.writeMessage("Yes");
                else if (inputChecker.placeOneElement())
                    ConsoleHelper.writeMessage("Yes");           // Places the rest of blocks to build the wall
                else ConsoleHelper.writeMessage("No");           // Prints "No" in case we didn't finish the wall
            }
            ConsoleHelper.writeMessage("");
        } catch (IOException e) {
            System.out.println(e.toString() + " in ParseLine");
        } catch (InterruptOperationException ignored){}
    }
}