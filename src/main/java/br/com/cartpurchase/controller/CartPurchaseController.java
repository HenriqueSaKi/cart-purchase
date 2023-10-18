package br.com.cartpurchase.controller;

import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CartPurchaseController {

    @PostMapping("/cart/add_to_cart")
    public ResponseEntity<Item> addItemsToCart (@RequestBody AddItem addItem) {
       return ResponseEntity.status(HttpStatus.OK).body(new Item());
    }

    @GetMapping("/cart/list_cart_items")
    public ResponseEntity<List<Item>> listCartItems () {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

    @DeleteMapping("/cart/remove_item/{cart_item_id}")
    public ResponseEntity<String> removeItem (@PathVariable String cartItemId) {
        String productTitle = "";
        return ResponseEntity.status(HttpStatus.OK)
                .body("Item " + cartItemId + " - " + productTitle +
                        " was removed successfully!");
    }

    @PostMapping("/cart/place_order")
    public ResponseEntity<List<Item>> placeOrder () {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

}
