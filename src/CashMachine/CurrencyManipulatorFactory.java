package CashMachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 04.11.2015.
 */

public final class CurrencyManipulatorFactory {
    static Map<String, CurrencyManipulator> currencyManipulatorMap = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        CurrencyManipulator current;
        if(currencyManipulatorMap.containsKey(currencyCode)){
            return currencyManipulatorMap.get(currencyCode);
        } else{
            current = new CurrencyManipulator(currencyCode);
            currencyManipulatorMap.put(currencyCode, current);
            return current;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currencyManipulatorMap.values();
    }

    private CurrencyManipulatorFactory()
    {
    }
}
