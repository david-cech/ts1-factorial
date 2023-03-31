package hw04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hw04.shop.*;
import hw04.storage.NoItemInStorage;

public class EshopControllerTest {

    private int[] itemCount;
    private Item[] storageItems;


    @BeforeEach
    public void setUpEshop() {
        EShopController.storage = null;
        EShopController.startEShop();

        itemCount = new int[]{10, 10, 4, 5, 10, 2};

        storageItems = new Item[]{
                new StandardItem(0, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(1, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(2, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(3, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(4, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(5, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        for (int i = 0; i < storageItems.length; i++) {
            EShopController.storage.insertItems(storageItems[i], itemCount[i]);
        }
    }

    @Test
    public void EmptyShoppingCartIsPurchased() {
        ShoppingCart sc = EShopController.newCart();
        Assertions.assertEquals(0, sc.getItemsCount());
        Assertions.assertEquals(0, sc.getTotalPrice());
        int size = EShopController.orders.size();
        Assertions.assertDoesNotThrow(() -> {
            EShopController.purchaseShoppingCart(sc, "John Doe", "Foo");
        });
        Assertions.assertEquals(size, EShopController.orders.size());
        for (int i = 0; i < storageItems.length; i++) {
            Assertions.assertEquals(itemCount[i], EShopController.storage.getItemCount(i));
        }
    }

    @Test
    public void ShoppingCartIsFilledThenEmptiedAndPurchased() {
        ShoppingCart sc = EShopController.newCart();
        sc.addItem(storageItems[1]);
        sc.removeItem(storageItems[1].getID());
        Assertions.assertEquals(0, sc.getItemsCount());
        Assertions.assertEquals(0, sc.getTotalPrice());
        int size = EShopController.orders.size();
        Assertions.assertDoesNotThrow(() -> {
            EShopController.purchaseShoppingCart(sc, "John Doe", "Foo");
        });
        Assertions.assertEquals(size, EShopController.orders.size());
        for (int i = 0; i < storageItems.length; i++) {
            Assertions.assertEquals(itemCount[i], EShopController.storage.getItemCount(i));
        }
    }

    @Test
    public void ShoppingCartIsFilledAndPurchased() {
        ShoppingCart sc = EShopController.newCart();
        sc.addItem(storageItems[0]);
        sc.addItem(storageItems[1]);
        Assertions.assertEquals(2, sc.getItemsCount());
        Assertions.assertEquals(storageItems[0].getPrice() + storageItems[1].getPrice(), sc.getTotalPrice());
        int size = EShopController.orders.size();
        Assertions.assertDoesNotThrow(() -> {
            EShopController.purchaseShoppingCart(sc, "John Doe", "Foo");
        });
        Assertions.assertEquals(size + 1, EShopController.orders.size());
        Assertions.assertEquals(itemCount[0] - 1, EShopController.storage.getItemCount(0));
        Assertions.assertEquals(itemCount[1] - 1, EShopController.storage.getItemCount(1));
        for (int i = 2; i < storageItems.length; i++) {
            Assertions.assertEquals(itemCount[i], EShopController.storage.getItemCount(i));
        }
    }

    @Test
    public void ShoppingCartContainsMoreItemsThanInStorage() {
        ShoppingCart sc = EShopController.newCart();
        for (int i = 0; i <= itemCount[0]; i++) {
            sc.addItem(storageItems[0]);
        }
        int size = EShopController.orders.size();
        Assertions.assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(sc, "John Doe", "Foo");
        });
        Assertions.assertEquals(size, EShopController.orders.size());
        Assertions.assertEquals(0, EShopController.storage.getItemCount(storageItems[0].getID()));
        for (int i = 1; i < storageItems.length; i++) {
            Assertions.assertEquals(itemCount[i], EShopController.storage.getItemCount(i));
        }
    }

    @Test
    public void ShoppingCartContainsUnknownItem() {
        ShoppingCart sc = EShopController.newCart();
        sc.addItem(storageItems[0]);
        sc.addItem(new StandardItem(20, "Unknown", 0.88f, "Unknown", 202));
        int size = EShopController.orders.size();
        Assertions.assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(sc, "John Doe", "Foo");
        });
        Assertions.assertEquals(size, EShopController.orders.size());
        Assertions.assertEquals(itemCount[0]-1, EShopController.storage.getItemCount(storageItems[0].getID()));
        for (int i = 1; i < storageItems.length; i++) {
            Assertions.assertEquals(itemCount[i], EShopController.storage.getItemCount(i));
        }
    }

}
