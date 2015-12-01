package WallOfBricks;

/**
 * Created by Artem on 23.11.2015.
 */

public enum Operation {
    PARSE_FILE,
    PARSE_INPUT,
    EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i){
        switch (i){
            case 1: return Operation.PARSE_INPUT;
            case 2: return Operation.PARSE_FILE;
            case 3: return Operation.EXIT;
            default: throw new IllegalArgumentException();
        }
    }
}
