package UI;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    Scanner scanner;

    public UserIOConsoleImpl()
    {
        scanner = new Scanner(System.in);
    }

    public void print(String message)
    {
        System.out.println(message);
    }

    public String readString(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt)
    {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal num;
        do {
            System.out.println(prompt);
            num = new BigDecimal(scanner.nextLine());
        }
        while(num.doubleValue() < min.doubleValue() || num.doubleValue() > max.doubleValue());

        return num;
    }
}
