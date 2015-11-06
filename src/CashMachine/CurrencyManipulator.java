package CashMachine;

import CashMachine.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Artem on 04.10.2015.
 */

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination)){
            denominations.put(denomination, denominations.get(denomination)+count);
        } else
            denominations.put(denomination, count);
    }
    public int getTotalAmount(){
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> money : denominations.entrySet()){
            totalAmount += money.getKey()*money.getValue();
        }
        return totalAmount;
    }
    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int amount = expectedAmount;
        Map<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()){
            list.add(pair.getKey());
        }
        Collections.sort(list);
        Collections.reverse(list);

        Map <Integer, Integer> result = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer nominal : list){
            int key = nominal;
            int value = temp.get(nominal);
            while(true){
                if(amount < key || value <= 0){
                    temp.put(nominal, value);
                    break;
                }
                amount -= key;
                value--;

                if(result.containsKey(key))
                    result.put(key, result.get(key)+1);
                else
                    result.put(key, 1);
            }
        }
        if (amount > 0){
            throw new NotEnoughMoneyException();
        }else {
            for (Map.Entry<Integer, Integer> pair : result.entrySet()){
                ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
            }

            denominations.clear();
            denominations.putAll(temp);
        }
        return result;
    }
    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }
}
