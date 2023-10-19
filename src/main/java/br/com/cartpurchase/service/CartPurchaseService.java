package br.com.cartpurchase.service;

import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartPurchaseService {

    Item saveInCart(ItemDTO itemDTO);

    List<Item> getCartItems();

    Item findItemById(Integer id) throws Exception;

    void deleteById(String id);

}
