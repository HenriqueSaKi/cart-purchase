package br.com.cartpurchase.mock;

import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Dimensions;

import java.math.BigDecimal;

public class AddItemMock {
    
    public AddItem getItem1() {
        AddItem item = new AddItem();
        item.setProductTitle("Water Bottle 1L");
        item.setDescription("The portable water can be used in various occasions. The gym, camping, traveling, as well as in the office and at home.");
        item.setBrandName("Xiaomi");
        item.setMaterial("Plastic");
        item.setColor("Red");
        item.setQuantity(1);
        item.setDimensions(new Dimensions());
        item.getDimensions().setLength(new BigDecimal("10.91"));
        item.getDimensions().setWidth(new BigDecimal("3.11"));
        item.getDimensions().setHeight(new BigDecimal("3.03"));
        item.getDimensions().setUnitOfMeasurement("inches");
        item.setPrice(new BigDecimal("9.99"));
        return item;

    }

    public AddItem getItem2() {
        AddItem item = new AddItem();
        item.setProductTitle("MIULEE 18x18 Pillow Inserts Set of 2");
        item.setDescription("MIULEE 18x18 Pillow Inserts Set of 2, Square Decorative Throw Pillows Premium Fluffy Pillow Forms Sham Stuffer for Living Room Sofa Couch Bed");
        item.setBrandName("MIULEE");
        item.setMaterial("Polyester");
        item.setColor("White");
        item.setQuantity(1);
        item.getDimensions().setLength(new BigDecimal("18"));
        item.getDimensions().setWidth(new BigDecimal("18"));
        item.getDimensions().setHeight(new BigDecimal("5"));
        item.getDimensions().setUnitOfMeasurement("inches");
        item.setPrice(new BigDecimal("9.99"));
        return item;

    }
    
}
