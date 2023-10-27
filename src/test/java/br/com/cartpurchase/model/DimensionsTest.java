package br.com.cartpurchase.model;

import br.com.cartpurchase.mock.DimensionsMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DimensionsTest {

    @Test
    public void testEquals() {
        Dimensions dimensions = new DimensionsMock().getDimensions();
        Assertions.assertTrue(dimensions.equals(dimensions));
        Assertions.assertFalse(dimensions.equals(null));

        Dimensions dimensions2 = new DimensionsMock().getDimensions();
        Assertions.assertTrue(dimensions.equals(dimensions2));

    }

    @Test
    public void testToString() {
        Dimensions dimensions = new DimensionsMock().getDimensions();
        String sb = "Dimensions{" +
                "length=" + dimensions.getLength() +
                ", width=" + dimensions.getWidth() +
                ", height=" + dimensions.getHeight() +
                ", unitOfMeasurement='" + dimensions.getUnitOfMeasurement() + '\'' +
                '}';

        Assertions.assertEquals(sb, dimensions.toString());

    }

}
