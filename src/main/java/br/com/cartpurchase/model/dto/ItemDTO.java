package br.com.cartpurchase.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TB_ITEM_CARRINHO")
public class ItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productTitle;
    private String description;
    private String brandName;
    private String material;
    private String color;
    private Integer quantity;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String unitOfMeasurement;
    private BigDecimal price;

}