package hw04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hw04.shop.Order;
import hw04.shop.ShoppingCart;
import hw04.shop.StandardItem;


public class OrderTest {

    @Test
    public void Order_ConstructorWithStateSetsShoppingCart_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar", 3);
        sc.addItem(new StandardItem(1, "StandardItem", 0.99f, "MyCategory", 3));

        Assertions.assertEquals(sc.getCartItems(), o.getItems());
    }

    @Test
    public void Order_ConstructorWithStateSetsCustomerName_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar", 3);

        Assertions.assertEquals("Foo", o.getCustomerName());
    }

    @Test
    public void Order_ConstructorWithStateSetsCustomerAddress_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar", 3);

        Assertions.assertEquals("Bar", o.getCustomerAddress());
    }

    @Test
    public void Order_ConstructorWithStateSetsState_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar", 3);

        Assertions.assertEquals(3, o.getState());
    }

    @Test
    public void Order_ConstructorWithoutStateSetsShoppingCart_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar");
        sc.addItem(new StandardItem(1, "StandardItem", 0.99f, "MyCategory", 3));

        Assertions.assertEquals(sc.getCartItems(), o.getItems());
    }

    @Test
    public void Order_ConstructorWithoutStateSetsCustomerName_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar");

        Assertions.assertEquals("Foo", o.getCustomerName());
    }

    @Test
    public void Order_ConstructorWithoutStateSetsCustomerAddress_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar");

        Assertions.assertEquals("Bar", o.getCustomerAddress());
    }

    @Test
    public void Order_ConstructorWithoutStateSetsStateZero_Order() {
        ShoppingCart sc = new ShoppingCart();
        Order o = new Order(sc, "Foo", "Bar");

        Assertions.assertEquals(0, o.getState());
    }

    @Test
    public void Order_ConstructorWithoutStateNullShoppingCart_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Order(null, "Foo", "Bar");
        });
    }

    @Test
    public void Order_ConstructorWithStateNullShoppingCart_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Order(null, "Foo", "Bar", 3);
        });
    }
}
