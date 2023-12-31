package br.com.cartpurchase.controller;

import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.EmailDTO;
import br.com.cartpurchase.model.dto.ItemDTO;
import br.com.cartpurchase.service.CartPurchaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartPurchaseController implements CartPurchaseApi{

    @Autowired
    CartPurchaseServiceImpl service;

    private static final String EMPTY_CART_MESSAGE = "Your cart is empty!";

    public ResponseEntity<Item> addItemsToCart (@RequestBody AddItem addItem) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(addItem, itemDTO);
        BeanUtils.copyProperties(addItem.getDimensions(), itemDTO);

        Item item = service.saveInCart(itemDTO);

        return ResponseEntity.status(HttpStatus.OK).body(item);

    }

    public ResponseEntity<Object> listCartItems () {
        List<Item> itemList = service.getCartItems();
        if(!itemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(itemList);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EMPTY_CART_MESSAGE);
        }
    }

    public ResponseEntity<String> removeItem (@PathVariable("cart_item_id") String cartItemId) {
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
                    .body(EMPTY_CART_MESSAGE);
        }

    }

    public ResponseEntity<String> placeOrder (@RequestBody EmailDTO emailDTO) {
        List<Item> items = service.getCartItems();

        if(!items.isEmpty()) {
            service.sendMessage2Queue(emailDTO, items);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Thank you for your order! We will send you an order confirmation to example@email.com shortly.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EMPTY_CART_MESSAGE);
        }

    }

}
