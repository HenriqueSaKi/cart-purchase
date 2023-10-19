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
import java.util.Objects;

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
  public Dimensions length(BigDecimal length) {
    this.length = length;
    return this;
  }

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

  public Dimensions width(BigDecimal width) {
    this.width = width;
    return this;
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

  public Dimensions height(BigDecimal height) {
    this.height = height;
    return this;
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

  public Dimensions unitOfMeasurement(String unitOfMeasurement) {
    this.unitOfMeasurement = unitOfMeasurement;
    return this;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dimensions dimensions = (Dimensions) o;
    return Objects.equals(this.length, dimensions.length) &&
        Objects.equals(this.width, dimensions.width) &&
        Objects.equals(this.height, dimensions.height) &&
        Objects.equals(this.unitOfMeasurement, dimensions.unitOfMeasurement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(length, width, height, unitOfMeasurement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dimensions {\n");
    
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    unitOfMeasurement: ").append(toIndentedString(unitOfMeasurement)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
