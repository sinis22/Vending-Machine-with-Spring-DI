package DTO;

import java.math.BigDecimal;

public enum Coins {
    // list of coins
    PENNIES(new BigDecimal(0.01)),
    NICKLES(new BigDecimal(0.05)),
    DIMES(new BigDecimal(0.1)),
    QUARTERS(new BigDecimal(0.25));

    private final BigDecimal value;

    Coins(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue()
    {
        return value;
    }
}
