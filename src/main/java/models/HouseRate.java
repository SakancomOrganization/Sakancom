package models;

import exceptions.UnacceptableValueException;

import java.util.HashMap;
import java.util.Map;

public class HouseRate {
    private final Map<User, Integer> ratesMap;

    public HouseRate() {
        ratesMap = new HashMap<>();
    }

    public double getTotalRate()throws ArithmeticException {
        int sumOfRates = 0;
        for(int rate : ratesMap.values()) {
            sumOfRates += rate;
        }
        return (double) sumOfRates / ratesMap.size();
    }

    public void setUserRate(User newRater, int newRate) throws UnacceptableValueException {
        // the rate must be between 0 and 5
        if(newRate < 0 || newRate > 5) {
            throw new UnacceptableValueException("The rate must be between 0 and 5!");
        }

        // the user cannot rate the house more than one time (replace)
        if(ratesMap.containsKey(newRater))
            ratesMap.replace(newRater, newRate);

        //else, add the rate
        ratesMap.put(newRater, newRate);
    }
}
