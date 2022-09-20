package UI;

import java.math.BigDecimal;

public interface UserIO {
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
}
