package br.com.cartpurchase.mock;

import br.com.cartpurchase.model.dto.ItemDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDTOMock {

    public List<ItemDTO> getItemsList() {
        return new ArrayList<>(
                List.of(
                        getItem1(),
                        getItem2()
                )
        );
    }

    public ItemDTO getItem1() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1);
        itemDTO.setProductTitle("Water Bottle 1L");
        itemDTO.setDescription("The portable water can be used in various occasions. The gym, camping, traveling, as well as in the office and at home.");
        itemDTO.setBrandName("Xiaomi");
        itemDTO.setMaterial("Plastic");
        itemDTO.setColor("Red");
        itemDTO.setQuantity(1);
        itemDTO.setLength(new BigDecimal("10.91"));
        itemDTO.setWidth(new BigDecimal("3.11"));
        itemDTO.setHeight(new BigDecimal("3.03"));
        itemDTO.setUnitOfMeasurement("inches");
        itemDTO.setPrice(new BigDecimal("9.99"));
        return itemDTO;

    }

    public ItemDTO getItem2() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(2);
        itemDTO.setProductTitle("MIULEE 18x18 Pillow Inserts Set of 2");
        itemDTO.setDescription("MIULEE 18x18 Pillow Inserts Set of 2, Square Decorative Throw Pillows Premium Fluffy Pillow Forms Sham Stuffer for Living Room Sofa Couch Bed");
        itemDTO.setBrandName("MIULEE");
        itemDTO.setMaterial("Polyester");
        itemDTO.setColor("White");
        itemDTO.setQuantity(1);
        itemDTO.setLength(new BigDecimal("18"));
        itemDTO.setWidth(new BigDecimal("18"));
        itemDTO.setHeight(new BigDecimal("5"));
        itemDTO.setUnitOfMeasurement("inches");
        itemDTO.setPrice(new BigDecimal("9.99"));
        return itemDTO;

    }

}
