package SERVICE;


import DAO.VendingMachineAudioDao;
import DAO.VendingMachineDao;
import DAO.VendingMachinePersistenceException;
import DTO.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceImpl implements VendingMachineService {
    private final VendingMachineDao dao;
    private final VendingMachineAudioDao auditDao;


    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        return dao.addItem(item);
    }

    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        return dao.listAllItems()
                .stream()
                .filter(item -> item.getInventory() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        return dao.removeItem(item);
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        Item item = dao.getItem(name);
        if(item.getInventory() == 0)
        {
            throw new VendingMachineNoItemInventoryException("No item found with " + name);
        }
        return item;
    }
    @Override
    public void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException {
        if(item == null) {
            throw new VendingMachinePersistenceException("Null item");
        }
        else {
            dao.changeInventoryCount(item, newCount);
        }
    }

    @Override
    public BigDecimal vendItem(BigDecimal money, Item item) throws VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        if(item.getPrice().compareTo(money) > 0)
        {
            throw new VendingMachineInsufficientFundsException("Not enough funds in machine");

        }
        else if(item.getInventory() <= 0)
        {
            throw new VendingMachineNoItemInventoryException("Item inventory is empty");
        }
        else {
            changeInventoryCount(item, item.getInventory() - 1);
            auditDao.writeAuditEntry(
                    "Item " + item.getName() + " vended." + "Number in Inventory: " + item.getInventory());
        }
        return money.subtract(item.getPrice());
    }
    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAudioDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
}
