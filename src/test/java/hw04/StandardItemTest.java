package hw04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import hw04.shop.StandardItem;

import java.util.stream.Stream;

public class StandardItemTest {

    @Test
    public void StandardItem_ConstructorSetsProperties_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals(1, item.getID());
        Assertions.assertEquals("TestItem", item.getName());
        Assertions.assertEquals(0.99f, item.getPrice());
        Assertions.assertEquals("TestCategory", item.getCategory());
        Assertions.assertEquals(10, item.getLoyaltyPoints());
    }

    @Test
    public void copy_CopiesProperties_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getID(), copy.getID());
        Assertions.assertEquals(item.getName(), copy.getName());
        Assertions.assertEquals(item.getPrice(), copy.getPrice());
        Assertions.assertEquals(item.getCategory(), copy.getCategory());
        Assertions.assertEquals(item.getLoyaltyPoints(), copy.getLoyaltyPoints());
    }

    @Test
    public void copy_CopyDoesNotChangeWhenOriginalChanges_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setName("NewName");
        item.setID(2);
        item.setPrice(2.0f);
        item.setCategory("NewCategory");
        item.setLoyaltyPoints(20);

        Assertions.assertEquals(1, copy.getID());
        Assertions.assertEquals("TestItem", copy.getName());
        Assertions.assertEquals(0.99f, copy.getPrice());
        Assertions.assertEquals("TestCategory", copy.getCategory());
        Assertions.assertEquals(10, copy.getLoyaltyPoints());
    }

    @Test
    public void equals_EqualStandardItem_True() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem other = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        Assertions.assertEquals(other, item);
    }

    @ParameterizedTest
    @MethodSource("equalsProvider")
    public void equals_DifferentObject_False(Object o) {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        Assertions.assertNotEquals(o, item);
    }

    public static Stream<Arguments> equalsProvider() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of("Foo"),
                Arguments.of(new StandardItem(2, "TestItem", 0.99f, "TestCategory", 10)),
                Arguments.of(new StandardItem(1, "FooItem", 0.99f, "TestCategory", 10)),
                Arguments.of(new StandardItem(1, "TestItem", 3f, "TestCategory", 10)),
                Arguments.of(new StandardItem(1, "TestItem", 0.99f, "FooCategory", 10)),
                Arguments.of(new StandardItem(1, "TestItem", 0.99f, "TestCategory", 300))
                );
    }
}
