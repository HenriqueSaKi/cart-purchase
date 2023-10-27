package br.com.cartpurchase.model;

import br.com.cartpurchase.mock.ItemMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void testEquals() {
        Item item = new ItemMock().getItem1();
        Assertions.assertTrue(item.equals(item));
        Assertions.assertFalse(item.equals(null));

        Item item2 = new ItemMock().getItem1();
        Assertions.assertTrue(item.equals(item2));

    }

    @Test
    public void testToString() {
        Item item = new ItemMock().getItem1();
        String sb = "Item{" +
                "id=" + item.getId() +
                ", productTitle='" + item.getProductTitle() + '\'' +
                ", description='" + item.getDescription() + '\'' +
                ", brandName='" + item.getBrandName() + '\'' +
                ", material='" + item.getMaterial() + '\'' +
                ", color='" + item.getColor() + '\'' +
                ", quantity=" + item.getQuantity() +
                ", dimensions=" + item.getDimensions() +
                ", price=" + item.getPrice() +
                '}';

        Assertions.assertEquals(sb, item.toString());

    }

}
