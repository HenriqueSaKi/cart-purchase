package br.com.cartpurchase.mock;

import br.com.cartpurchase.model.Dimensions;

import java.math.BigDecimal;

public class DimensionsMock {

    public Dimensions getDimensions() {
        Dimensions dimensions = new Dimensions();
        dimensions.setLength(new BigDecimal("10.91"));
        dimensions.setWidth(new BigDecimal("3.11"));
        dimensions.setHeight(new BigDecimal("3.03"));
        dimensions.setUnitOfMeasurement("inches");

        return dimensions;

    }

}
