package br.com.cartpurchase.model;

import br.com.cartpurchase.mock.AddItemMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddItemTest {

    @Test
    public void testEquals() {
        AddItem addItem = new AddItemMock().getItem1();
        Assertions.assertTrue(addItem.equals(addItem));
        Assertions.assertFalse(addItem.equals(null));

        AddItem addItem3 = new AddItemMock().getItem1();
        Assertions.assertTrue(addItem.equals(addItem3));

    }

    @Test
    public void testToString() {
        AddItem addItem = new AddItemMock().getItem1();
        String sb = "AddItem{" +
                "productTitle='" + addItem.getProductTitle() + '\'' +
                ", description='" + addItem.getDescription() + '\'' +
                ", brandName='" + addItem.getBrandName() + '\'' +
                ", material='" + addItem.getMaterial() + '\'' +
                ", color='" + addItem.getColor() + '\'' +
                ", quantity=" + addItem.getQuantity() +
                ", dimensions=" + addItem.getDimensions() +
                ", price=" + addItem.getPrice() +
                '}';

        Assertions.assertEquals(sb, addItem.toString());

    }

}
