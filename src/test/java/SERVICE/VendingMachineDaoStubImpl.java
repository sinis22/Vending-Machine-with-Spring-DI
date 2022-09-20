package SERVICE;


import DAO.VendingMachineDao;
import DTO.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item item;

    public VendingMachineDaoStubImpl()
    {
        item = new Item("Chips", new BigDecimal(2.99), 2);
    }

    @Override
    public Item getItem(String name) {
        if(name.equals(item.getName()))
        {
            return item;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Item> listAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        return itemList;
    }

    @Override
    public Item addItem(Item item) {
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        return null;
    }

    @Override
    public void changeInventoryCount(Item item, int newCount) {
        if(item != null) {
            item.setInventory(newCount);
        }
    }
}
