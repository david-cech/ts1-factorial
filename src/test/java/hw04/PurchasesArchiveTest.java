package hw04;

import hw04.archive.ItemPurchaseArchiveEntry;
import hw04.archive.PurchasesArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import hw04.shop.Item;
import hw04.shop.Order;
import hw04.shop.ShoppingCart;
import hw04.shop.StandardItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.mockConstruction;

public class PurchasesArchiveTest {

    private ItemPurchaseArchiveEntry mockEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
    private ArrayList<Order> mockOrderArchive = Mockito.mock(ArrayList.class);
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
    private Item ref = new StandardItem(1, "Foo", 0.99f, "Bar", 10);
    private ShoppingCart sc;
    private Order o;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpOrder() {
        sc = new ShoppingCart();
        sc.addItem(ref);
        o = new Order(sc, "Jon", "Doe");
    }


    public void setUpOutStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreOutStream() {
        System.setOut(originalOut);
    }

    @Test
    public void printItemPurchaseStatistics_NoPurchases_PrintsOnlyHeader() {
        PurchasesArchive pa = new PurchasesArchive();
        setUpOutStream();
        pa.printItemPurchaseStatistics();
        Assertions.assertEquals("ITEM PURCHASE STATISTICS:\n", outContent.toString());
    }

    @Test
    public void printItemPurchaseStatistics_TwoItemsPurchased_HeaderAndItemPurchaseArchiveEntryStrings() {
        PurchasesArchive pa = new PurchasesArchive();
        Item other = new StandardItem(2, "Lorem", 0.99f, "Ipsum", 10);
        sc.addItem(other);
        pa.putOrderToPurchasesArchive(o);
        setUpOutStream();
        pa.printItemPurchaseStatistics();
        Assertions.assertEquals("ITEM PURCHASE STATISTICS:\n" +
                "ITEM  Item   ID 1   NAME Foo   CATEGORY Bar   PRICE 0.99   LOYALTY POINTS 10   HAS BEEN SOLD 1 TIMES\n" +
                "ITEM  Item   ID 2   NAME Lorem   CATEGORY Ipsum   PRICE 0.99   LOYALTY POINTS 10   HAS BEEN SOLD 1 TIMES\n", outContent.toString());
    }

    @Test
    public void getHowManyTimesHasBeenItemSold_NoItemSold_0() {
        PurchasesArchive pa = new PurchasesArchive(itemPurchaseArchive, mockOrderArchive);
        Assertions.assertEquals(0, pa.getHowManyTimesHasBeenItemSold(ref));
    }

    @Test
    public void getHowManyTimesHasBeenItemSold_SoldTwoTimes_2() {
        sc.addItem(ref);
        PurchasesArchive pa = new PurchasesArchive();
        pa.putOrderToPurchasesArchive(o);
        Assertions.assertEquals(2, pa.getHowManyTimesHasBeenItemSold(ref));
    }

    @Test
    public void putOrderToPurchases_NewOrder_OrderIsAddedToOrderArchive() {
        PurchasesArchive pa = new PurchasesArchive(itemPurchaseArchive, mockOrderArchive);
        pa.putOrderToPurchasesArchive(o);
        Mockito.verify(mockOrderArchive).add(o);
    }


    @Test
    public void putOrderToPurchasesArchive_ArchiveContainsItem_IncreasesSoldCountByOne() {
        itemPurchaseArchive.put(ref.getID(), mockEntry);

        PurchasesArchive pa = new PurchasesArchive(itemPurchaseArchive, mockOrderArchive);

        pa.putOrderToPurchasesArchive(o);

        Mockito.verify(mockEntry).increaseCountHowManyTimesHasBeenSold(1);
    }

    @Test
    public void putOrderToPurchasesArchive_NoEntryInItemPurchaseArchive_CreatesNewItemPurchaseArchiveEntry() {
        PurchasesArchive pa = new PurchasesArchive(itemPurchaseArchive, mockOrderArchive);

        try (MockedConstruction mocked = mockConstruction(ItemPurchaseArchiveEntry.class)) {
            pa.putOrderToPurchasesArchive(o);

            Assertions.assertEquals(1, mocked.constructed().size());
        }
    }


}
