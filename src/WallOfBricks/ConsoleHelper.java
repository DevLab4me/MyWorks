package WallOfBricks;

import WallOfBricks.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Artem on 23.11.2015.
 */

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String string){
        System.out.println(string);
    }

    public static String readString() throws InterruptOperationException {
        String message = "";
        try{
            message = reader.readLine();
            if(message.equalsIgnoreCase(Operation.EXIT.toString())){
                throw new InterruptOperationException();
            }
        } catch (IOException ignored){

        }
        return message;
    }

    public static Operation askOperation()throws InterruptOperationException {
        Operation operation;
        while(true){
            writeMessage("Please, type the number of desired operation or type 'exit' if you wish to go back/exit \n" +
                    "1 - Build a wall from Console \n" + "2 - Build a wall from File \n" + "3 - Exit");
            String answer = readString();
            try {
                int choice = Integer.parseInt(answer);
                operation = Operation.getAllowableOperationByOrdinal(choice);
                break;
            } catch (IllegalArgumentException e) {
                writeMessage("Invalid data. Please, type the number of desired operation or type 'exit' if you wish to go back/exit");
            }
        }
        return operation;
    }

    /** Returns an array with wall parameters:
     * width of suggested wall;
     * height of suggested wall*/

    public static int[] askWallSize() throws InterruptOperationException {
        int [] wallSize;
        writeMessage("Please, enter width and height parameters of the wall (two digits separated by space): ");
        while(true){
            String [] line = readString().split(" ");
            int width, height;
            try{
                width = Integer.parseInt(line[0]);
                height = Integer.parseInt(line[1]);
                wallSize = new int[]{width, height};
            }catch (Exception e){
                writeMessage(e.toString());
                continue;
            }
            if (width <= 0 || height <= 0 || line.length > 2){
                writeMessage("Invalid data. Please, try again or type 'exit' in order to go back to the main menu");
                continue;
            }
            break;
        }
        return wallSize;
    }

    /** Returns an array with wall specifications (where to put bricks and gaps)
     * @param width length of suggested wall line;
     * @param height number of suggested wall lines*/

    public static ArrayList<String> askWallSpecs(int width, int height) throws InterruptOperationException{
        ArrayList<String> wallSpecs = new ArrayList<String>();

        writeMessage("Please, enter a specification for each brick line of the wall ('1' - brick; '0' - gap):");
        int countLines = 0;
        while(countLines < height){
            String line = readString().trim();
            if(line.length() == width){
                if(line.replaceAll("[0-1]","").length()==0){     // controls a correct input values for each line
                    wallSpecs.add(line);
                    countLines++;
                } else {
                    writeMessage("Line should be built only with '1' and '0' values");
                }
            } else
                writeMessage("Please, try again. String length should be the same as a width of the wall");
        }
        return wallSpecs;
    }

    public static int askBrickTypeQuantity() throws InterruptOperationException {
        writeMessage("Please enter the amount of brick types you want to use:");
        int answer;
        while(true) {
            try {
                answer = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                writeMessage("Please, enter a valid number:");
            }
        }
        return answer;
    }

    public static ArrayList<String> askBrickSpecs(int count) throws InterruptOperationException {
        ArrayList<String> bricks = new ArrayList<String>();
        int countLoops = count;
        writeMessage("Please, enter a brick specification: width, height and quantity (three digits separated by space):");
        while(countLoops > 0) {
            String[] line = readString().split(" ");
            int width, height, quantity;
            try {
                width = Integer.parseInt(line[0]);
                height = Integer.parseInt(line[1]);
                quantity = Integer.parseInt(line[2]);
                if (width <= 0 || width > 8 || height <= 0 || height > 8 || line.length > 3) {
                    writeMessage("Invalid data. Please, try again");
                } else {
                    bricks.add(width + " " + height + " " + quantity);
                    countLoops--;
                }
            } catch (Exception e) {
                writeMessage(e.toString());
            }
        }
        return bricks;
    }
}
