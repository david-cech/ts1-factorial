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
    public void StandardItem_ConstructorSetsId_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals(1, item.getID());
    }

    @Test
    public void StandardItem_ConstructorSetsName_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals("TestItem", item.getName());
    }

    @Test
    public void StandardItem_ConstructorSetsPrice_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals(0.99f, item.getPrice());
    }

    @Test
    public void StandardItem_ConstructorSetsCategory_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals("TestCategory", item.getCategory());
    }

    @Test
    public void StandardItem_ConstructorSetsLoyaltyPoints_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);

        Assertions.assertEquals(10, item.getLoyaltyPoints());
    }

    @Test
    public void copy_CopiesId_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getID(), copy.getID());
    }

    @Test
    public void copy_CopiesName_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getName(), copy.getName());
    }

    @Test
    public void copy_CopiesPrice_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getPrice(), copy.getPrice());
    }

    @Test
    public void copy_CopiesCategory_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getCategory(), copy.getCategory());
    }

    @Test
    public void copy_CopiesLoyaltyPoints_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getLoyaltyPoints(), copy.getLoyaltyPoints());
    }

    @Test
    public void copy_CopyIdDoesNotChangeWhenOriginalIdChanges_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setID(2);

        Assertions.assertEquals(1, copy.getID());
    }

    @Test
    public void copy_CopyNameDoesNotChangeWhenOriginalNameChanges_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setName("NewName");

        Assertions.assertEquals("TestItem", copy.getName());
    }

    @Test
    public void copy_CopyPriceDoesNotChangeWhenOriginalPriceChanges_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setPrice(2.0f);

        Assertions.assertEquals(0.99f, copy.getPrice());
    }

    @Test
    public void copy_CopyCategoryDoesNotChangeWhenOriginalCategoryChanges_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setCategory("NewCategory");

        Assertions.assertEquals("TestCategory", copy.getCategory());
    }

    @Test
    public void copy_CopyLoyaltyPointsDoNotChangeWhenOriginalLoyaltyPointsChange_StandardItem() {
        StandardItem item = new StandardItem(1, "TestItem", 0.99f, "TestCategory", 10);
        StandardItem copy = item.copy();
        item.setLoyaltyPoints(20);

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
