package DAO;

import DTO.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao{
    Map<String, Item> itemMap;
    private final String ITEM_FILE;
    private static final String DELIMITER = ",";

    //adding an item
    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        Item addedItem = itemMap.put(item.getName(), item);
        writeFile();
        return addedItem;
    }

    // listing all the items
    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        readFile();
        return new ArrayList<>(itemMap.values());
    }

    // getiing the item from stock
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        readFile();
        return itemMap.get(name);
    }

    // remove the item once taken
    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        readFile();
        Item removedItem = itemMap.remove(item.getName());
        writeFile();
        return removedItem;
    }

    //reduce product taken by 1
    @Override
    public void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException {
        item.setInventory(newCount);
        writeFile();
        readFile();
    }

    // inventory file
    public VendingMachineDaoFileImpl() throws VendingMachinePersistenceException {
        ITEM_FILE = System.getProperty("user.dir") + "/inventory.txt";
        itemMap = new HashMap<>();
        readFile();
    }

    public VendingMachineDaoFileImpl(String file) throws VendingMachinePersistenceException {
        ITEM_FILE = System.getProperty("user.dir") + "/" + file;
        itemMap = new HashMap<>();
        readFile();
    }

    private Item unmarshallItems(String itemAsText)
    {
        String[] data = itemAsText.split(DELIMITER);
        String name = data[0];
        BigDecimal price = new BigDecimal(data[1]);
        int inventory = Integer.parseInt(data[2]);

        return new Item(name, price, inventory);
    }

    private String marshallItems(Item item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getPrice()  + DELIMITER;
        itemAsText += item.getInventory()+ DELIMITER;
        itemAsText += "\n";
        return itemAsText;
    }

    // reading to file
    private void readFile() throws VendingMachinePersistenceException {
        try {
            Scanner scanner = new Scanner( new BufferedReader(new FileReader(ITEM_FILE)));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                Item currentItem = unmarshallItems(currentLine);
                itemMap.put(currentItem.getName(), currentItem);
            }
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            throw new VendingMachinePersistenceException("File not found", e);
        }
    }

    // writing to file
    private void writeFile() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE, false));
            List<Item> itemList = this.listAllItems();
            for(Item curItem : itemList) {
                out.write(marshallItems(curItem));
                out.flush();
            }out.close();
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save student data.", e);
        }
    }
}
