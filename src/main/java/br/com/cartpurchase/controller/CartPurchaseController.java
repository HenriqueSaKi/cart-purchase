package br.com.cartpurchase.controller;

import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.ItemDTO;
import br.com.cartpurchase.service.CartPurchaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CartPurchaseController {

    @Autowired
    CartPurchaseServiceImpl service;

    @PostMapping("/cart/add_to_cart")
    public ResponseEntity<Item> addItemsToCart (@RequestBody AddItem addItem) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(addItem, itemDTO);
        BeanUtils.copyProperties(addItem.getDimensions(), itemDTO);

        Item item = service.saveInCart(itemDTO);

        return ResponseEntity.status(HttpStatus.OK).body(item);

    }

    @GetMapping("/cart/list_cart_items")
    public ResponseEntity<Object> listCartItems () {
        List<Item> itemList = service.getCartItems();
        if(!itemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(itemList);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Your cart is empty!");
        }
    }

    @DeleteMapping("/cart/remove_item/{cartItemId}")
    public ResponseEntity<String> removeItem (@PathVariable String cartItemId) {
        List<Item> items = service.getCartItems();

        if(!items.isEmpty()) {
            try {
                String productTitle = service.findItemById(
                                Integer.parseInt(cartItemId))
                        .getProductTitle();

                service.deleteById(cartItemId);

                return ResponseEntity.status(HttpStatus.OK)
                        .body("Item " + cartItemId + " - " + productTitle +
                                " was removed successfully!");
            }
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Your cart is empty!");
        }

    }

    @GetMapping("/cart/place_order")
    public ResponseEntity<String> placeOrder () {
        List<Item> items = service.getCartItems();

        if(!items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Thank you for your order! We will send you an order confirmation to example@email.com shortly.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Your cart is empty!");
        }

    }

}
