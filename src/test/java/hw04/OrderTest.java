package hw04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hw04.shop.Order;
import hw04.shop.ShoppingCart;
import hw04.shop.StandardItem;


public class OrderTest {

    @Test
    public void Order_ConstructorWithState_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar", 3);
        sc.addItem(new StandardItem(1, "StandardItem", 0.99f, "MyCategory", 3));

        Assertions.assertEquals(sc.getCartItems(), o.getItems());
        Assertions.assertEquals("Foo", o.getCustomerName());
        Assertions.assertEquals("Bar", o.getCustomerAddress());
        Assertions.assertEquals(3, o.getState());
    }

    @Test
    public void Order_ConstructorWithoutState_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar");
        sc.addItem(new StandardItem(1, "StandardItem", 0.99f, "MyCategory", 3));

        Assertions.assertEquals(sc.getCartItems(), o.getItems());
        Assertions.assertEquals("Foo", o.getCustomerName());
        Assertions.assertEquals("Bar", o.getCustomerAddress());
        Assertions.assertEquals(0, o.getState());
    }

    @Test
    public void Order_ConstructorNullShoppingCart_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Order(null, "Foo", "Bar");
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Order(null, "Foo", "Bar", 3);
        });
    }
}
