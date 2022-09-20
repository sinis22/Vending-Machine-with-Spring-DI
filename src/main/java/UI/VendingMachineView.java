package UI;

import DTO.Coins;
import DTO.Item;

import java.math.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VendingMachineView {
    private final UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    //printing the menu
    public void  printMenu() {
        io.print("======Main Menu========");
        io.print("1. Add Money");
        io.print("2. List all Items");
        io.print("3. Buy Item");
        io.print("4. Quit");
    }
    // get the number choice from the menu
    public int getChoice()
    {
        return Integer.parseInt(io.readString("Please select an operation."));
    }

    // add money
    public BigDecimal addMoney()
    {
        io.print("====ADD FUNDS====");
        BigDecimal funds = io.readBigDecimal("Enter funds to add ($0 - $100): ", new BigDecimal(0), new BigDecimal(100));
        io.print("Funds added");
        io.readString("Please hit enter to continue.");
        return funds;
    }

// printing all items in the vending machine
    public void printAllItems(List<Item> itemList)
    {
        io.print("=======Vending Machine Items==========");
        for(int i = 0; i < itemList.size(); i++)
        {
            io.print(i + 1 + ": " + itemList.get(i));
        }
        io.print("=======================================");
        io.print(" ");
    }
    // choosing the item number based of the list given
    public int itemChoiceMessage() {
        return io.readInt("Please select the item number.");
    }
    public void invalidItem() {
        io.print("Invalid item entered. Please enter an item shown in machine.");
    }

    // show how much money is in the account
    public void showBalance(BigDecimal bal)
    {
        NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
        usdCostFormat.setMinimumFractionDigits( 2 );
        usdCostFormat.setMaximumFractionDigits( 2 );
        io.print("Current balance: " + usdCostFormat.format(bal.doubleValue()));
    }
    public void displayErrorMessage(String message)
    {
        io.print(message + '\n');
        io.readString("Please hit enter to continue.");
    }

    // printing the change given after a transaction
    public void Change(Map<Coins, Integer> changeMap)
    {
        io.print("Change: " + changeMap.get(Coins.QUARTERS) + " quarters, " +
                    changeMap.get(Coins.DIMES) + " dimes, " +
                    changeMap.get(Coins.NICKLES) + " nickles, " +
                    changeMap.get(Coins.PENNIES) + " pennies");
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner()
    {
        io.print(" ");
        io.print("Goodbye, thanks for using the vending machine, see you soon!");
    }
    public void displayUnknownCommandBanner() {
        io.print("Invalid input. Please input 1, 3, 3 or 4" + "\n");
    }

}
