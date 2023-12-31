/*
 * Cart Purchase Microservice
 * Cart purchase microservice
 *
 * OpenAPI spec version: 1.0.0
 * Contact: satoshikisaki@hotmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package br.com.cartpurchase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Dimensions
 */

@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-10-17T21:51:11.721328500-03:00[America/Sao_Paulo]")
public class Dimensions {

  @JsonProperty("length")
  private BigDecimal length = null;

  @JsonProperty("width")
  private BigDecimal width = null;

  @JsonProperty("height")
  private BigDecimal height = null;

  @JsonProperty("unitOfMeasurement")
  private String unitOfMeasurement = null;

  /**
  * Get length
  * @return length
  **/
  @Schema(example = "10.91", description = "")
  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  /**
  * Get width
  * @return width
  **/
  @Schema(example = "3.11", description = "")
  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  /**
  * Get height
  * @return height
  **/
  @Schema(example = "3.03", description = "")
  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  /**
  * Get unitOfMeasurement
  * @return unitOfMeasurement
  **/
  @Schema(example = "inches", description = "")
  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  public void setUnitOfMeasurement(String unitOfMeasurement) {
    this.unitOfMeasurement = unitOfMeasurement;
  }

}
