package DAO;

import DTO.Item;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;
    @BeforeAll
    static void setUpClass() {}

    @AfterAll
    static void tearDownClass() {}

    @BeforeEach
    void setUp() throws Exception {
        String testFile = "test.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @AfterEach
    void tearDown() {}

    @Test
    void getItem() throws Exception {
        Item item1 = testDao.getItem("Oreo");
        assertNotNull(item1, "Item must not be null");
        assertEquals(300, item1.getInventory(), "Inventory count should be 300");
        assertEquals(0.50, item1.getPrice().doubleValue(), "Cost should be 0.50");

        Item item2 = testDao.getItem("Doritos");
        assertNotNull(item2, "Item must not be null");
        assertEquals(0, item2.getInventory(), "Inventory count should be 0");
        assertEquals(3.45, item2.getPrice().doubleValue(), "Cost should be 3.45");

    }

    @Test
    void listAllItems() throws Exception {
        List<Item> testList = testDao.listAllItems();

        assertNotNull(testList, "Item list must not be null");
        assertEquals(6, testList.size(), "List should have 6 items");
        assertTrue(testList.contains(testDao.getItem("Swiss roll")), "List should have Swiss roll");
        assertTrue(testList.contains(testDao.getItem("Oreo")), "List should have Oreo");
        assertTrue(testList.contains(testDao.getItem("Doritos")), "List should have Doritos");
        assertTrue(testList.contains(testDao.getItem("Popcorn")), "List should have Popcorn");
        assertTrue(testList.contains(testDao.getItem("Coca Cola")), "List should have Coca Cola");
        assertTrue(testList.contains(testDao.getItem("Fanta")), "List should have Gum");
        assertFalse(testList.contains(testDao.getItem("")), "List should not have empty string");
    }

    @Test
    void changeInventoryCount() throws Exception {
        Item item1 = testDao.getItem("Popcorn");
        assertNotNull(item1, "Item must not be null");
        testDao.changeInventoryCount(item1, 5);
        assertEquals(5, item1.getInventory(), "Inventory count should be 5");

        Item item2 = testDao.getItem("Fanta");
        assertNotNull(item2, "Item must not be null");
        testDao.changeInventoryCount(item2, 0);
        assertEquals(0, item2.getInventory(), "Inventory count should be 0");
    }
}