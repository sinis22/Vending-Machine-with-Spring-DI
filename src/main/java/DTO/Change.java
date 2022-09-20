package DTO;


import SERVICE.VendingMachineInsufficientFundsException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Change {
    private final Map<Coins, Integer> Change;

    public Change()
    {
        Change = new HashMap<>();
    }

    public Map<Coins, Integer> getChange(BigDecimal funds, BigDecimal price) throws VendingMachineInsufficientFundsException {
        MathContext mc = new MathContext(4, RoundingMode.HALF_UP);

        BigDecimal Amount = funds.subtract(price);

        // checking if there is enough money
        if(Amount.doubleValue() < 0)
        {
             throw new VendingMachineInsufficientFundsException("Not enough funds");
        }
        BigDecimal[] remainder; // finding the remainder

        // calculating the change
        remainder = Amount.divideAndRemainder(Coins.QUARTERS.getValue(), mc);
        BigDecimal quarters = remainder[0];
        Change.put(Coins.QUARTERS, quarters.intValue());
        Amount = remainder[1];

        remainder = Amount.divideAndRemainder(Coins.DIMES.getValue(), mc);
        BigDecimal dimes = remainder[0];
        Change.put(Coins.DIMES, dimes.intValue());
        Amount = remainder[1];

        remainder = Amount.divideAndRemainder(Coins.NICKLES.getValue(), mc);
        BigDecimal nickles = remainder[0];
        Change.put(Coins.NICKLES, nickles.intValue());
        Amount = remainder[1];

        BigDecimal pennies = Amount.divide(Coins.PENNIES.getValue(), mc);
        Change.put(Coins.PENNIES, pennies.intValue());

        return Change;
    }
}
