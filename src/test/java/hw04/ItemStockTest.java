package hw04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import hw04.shop.StandardItem;
import hw04.storage.ItemStock;

public class ItemStockTest {

    private StandardItem testRef = new StandardItem(1, "Foo", 0.99f, "Bar", 10);

    @Test
    public void ItemStock_SetsProperties_ItemStock(){
        ItemStock is = new ItemStock(testRef);
        Assertions.assertEquals(testRef, is.getItem());
        Assertions.assertEquals(0, is.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10})
    public void increaseItemCount_IncreasesCount_count(int count){
        ItemStock is = new ItemStock(testRef);
        int old = is.getCount();
        is.increaseItemCount(count);
        Assertions.assertEquals(old+count, is.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 10})
    public void decreaseItemCount_DecreasesCount_count(int count){
        ItemStock is = new ItemStock(testRef);
        is.increaseItemCount(100);
        int old = is.getCount();
        is.decreaseItemCount(count);
        Assertions.assertEquals(old-count, is.getCount());
    }
}
