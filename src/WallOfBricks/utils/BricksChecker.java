package WallOfBricks.utils;

import WallOfBricks.ConsoleHelper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Artem on 23.11.2015.
 */

public class BricksChecker {

    private int wallSizeInPixels;        // Number of uncovered "1" in our wall matrix
    private int[] bricks;                // Array that contains a number of available bricks.
    private int[] wallGaps;              // Array that contains length of all gaps in the wall
    private int maxBrickSize;            // Constant which matches maximum available size of block
    private int mapLength = 0;           // Number of bricks in the wall

    public int getWallSizeInPixels() {
        return wallSizeInPixels;
    }

    /**Analyzes all received data and processes it
     * @param data - HashMap with a stored information of all specifications */
    public void processData(HashMap<String, String> data) throws IOException
    {
        String wallSize = data.get("wallSize");
        int width, height;
        width = getIntegerFromString(wallSize, 1);
        height = getIntegerFromString(wallSize, 2);

        wallGaps = new int[width * height];              // Creating an array that has enough size to store matrix in a line

        String wallSpec;
        for (int i = 0; i < height; i++) {
            wallSpec = data.get("wallSpec" + i);
            drawWallShape(wallSpec);
        }

        String countTypes = data.get("countTypes");      // Reading the the quantity of brick types
        int count = getIntegerFromString(countTypes, 1);
        String brick;
        bricks = new int[8*8];                           // Maximum possible length of array
        while (count-- > 0)                              // Reading the info about available bricks from the file
        {
            brick = data.get("brick" + count);
            int brickLength = getIntegerFromString(brick, 1)*getIntegerFromString(brick, 2);
            if (brickLength > maxBrickSize) maxBrickSize = brickLength;
            int brickCount = getIntegerFromString(brick, 3);
            bricks[brickLength-1] = brickCount;
        }
    }

    /**Returns an Integer value of a number from the given line
     * @param line - where to find the required number
     * @param index - ordinal number of the required number*/
    public int getIntegerFromString(String line, int index)
    {
        String number = "";
        int result = 0;
        String [] numbers = line.trim().split(" ");

        if (index == 1) number = numbers[0];
        else if (index == 2) number = numbers[1];
        else if (index == 3) number = numbers[2];

        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage("Please, check if you have a valid data in your file");
        }
        return result;
    }

    /**Stores a matrix of the wall in the static array according to the specifications
     * @param line - specification of a wall line: '1' - brick and '0' - gap*/
    public void drawWallShape(String line) throws IOException
    {
        int gap = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '1') gap++;
            else if (line.charAt(i) == '0') {
                if (gap > 0) {
                    wallGaps[mapLength++] = gap;
                    gap = 0;
                }
            } else throw new IOException("Wall format is incorrect");
        }
        if (gap > 0) wallGaps[mapLength++] = gap;
    }

    /** Places bricks into those gaps in the wall that exactly match to the length of the brick*/
    public void placeMatchingBlocks()
    {
        for (int i = 0; i < mapLength; i++) {
            int gapLength = wallGaps[i];
            if (gapLength <= maxBrickSize) {
                if (bricks[gapLength - 1] > 0) {
                    wallGaps[i] = 0;
                    bricks[gapLength - 1]--;
                    wallSizeInPixels -= gapLength;
                }
            }
        }
    }

    /**Checks if we have enough blocks length to build the wall*/
    public boolean isEnoughBlocks()
    {
        int pixelCount = 0;
        int pixelsNeeded = 0;

        for (int i = 0; i < maxBrickSize; i++)                           // Summing up all the lengths of bricks
            pixelCount += bricks[i] * (i + 1);

        for (int i = 0; i < mapLength; i++) pixelsNeeded += wallGaps[i]; // Summing up all lengths of gaps in the wall

        wallSizeInPixels = pixelsNeeded;

        return pixelCount >= pixelsNeeded;
    }

    /**Recursive function - tries to build the wall using a brute-force search
     @return 'true' - If the size of the wallGaps riches 0 - the wall has been built successfully. Otherwise, returns 'false'*/
    public boolean placeOneElement() {
        // It takes each brick starting from the biggest one and tries to place it into the wallGaps
        //(if such kind of bricks are available). If a brick is placed successfully, function calls itself.
        for (int i = maxBrickSize - 1; i >= 0; i--)
        {
            if (bricks[i] > 0) {
                for (int j = 0; j < mapLength; j++) {
                    if (i + 1 <= wallGaps[j]) {
                        wallGaps[j] -= (i + 1);
                        bricks[i]--;
                        wallSizeInPixels -= (i + 1);
                        if (wallSizeInPixels == 0) return true;
                        else {
                            if (placeOneElement()) return true;
                            wallGaps[j] += (i + 1);
                            bricks[i]++;
                            wallSizeInPixels += (i + 1);
                        }
                    }
                }

            }
        }
        return false;
    }
}
