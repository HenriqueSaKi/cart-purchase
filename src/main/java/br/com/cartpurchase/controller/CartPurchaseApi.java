package br.com.cartpurchase.controller;

import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.EmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-20T00:38:13.818188357Z[GMT]")
@Validated
public interface CartPurchaseApi {

    @Operation(summary = "Add items to cart", description = "Add items to cart", tags={ "cart" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = Item.class))) })
    @RequestMapping(value = "/cart/add_to_cart",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Item> addItemsToCart(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody AddItem addItem);


    @Operation(summary = "Returns a list of items added to the cart", description = "Returns a list of items added to the cart", tags={ "cart" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema =
                @Schema(implementation = Item.class)))),
            @ApiResponse(responseCode = "404", description = "empty cart", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Your cart is empty!"))) })
    @RequestMapping(value = "/cart/list_cart_items",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Object> listCartItems();


    @Operation(summary = "Place order", description = "Place order", tags={ "cart" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Thank you for your order! We will send you an order confirmation to example@email.com shortly."))),
            @ApiResponse(responseCode = "404", description = "empty cart", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Your cart is empty!"))) })
    @RequestMapping(value = "/cart/place_order",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<String> placeOrder(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody EmailDTO body) throws IOException;


    @Operation(summary = "Remove item", description = "Remove item", tags={ "cart" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Item 1 - Water Bottle was removed successfully!"))),
            @ApiResponse(responseCode = "400", description = "id not found", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Error message"))),
            @ApiResponse(responseCode = "404", description = "empty cart", content = @Content(mediaType = "application/json", schema =
                @Schema(implementation = String.class), examples = @ExampleObject(value = "Your cart is empty!"))) })
    @RequestMapping(value = "/cart/remove_item/{cart_item_id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<String> removeItem(@Parameter(in = ParameterIn.PATH, description = "ID of item in the cart", required=true, schema=@Schema()) @PathVariable("cart_item_id") String cartItemId);

}