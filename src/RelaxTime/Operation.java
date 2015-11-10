package MyWorks.src.RelaxTime;

/**
 * Created by Artem on 09.11.2015.
 */

public enum Operation {
    PASSWORD,
    END,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        switch (i){
            case 1: return Operation.PASSWORD;
            case 2: return Operation.END;
            case 3: return Operation.EXIT;
            default: throw new IllegalArgumentException();
        }
    }
}
