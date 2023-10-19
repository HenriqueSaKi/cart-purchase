package br.com.cartpurchase.service;

import br.com.cartpurchase.model.Dimensions;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.ItemDTO;
import br.com.cartpurchase.repository.CartPurchaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartPurchaseServiceImpl implements CartPurchaseService{

    @Autowired
    CartPurchaseRepository repository;

    @Override
    public Item saveInCart(ItemDTO itemDTO) {
        repository.save(itemDTO);

        Item item = new Item();
        Dimensions dimensions = new Dimensions();
        BeanUtils.copyProperties(itemDTO, item);
        BeanUtils.copyProperties(itemDTO, dimensions);
        item.setDimensions(dimensions);

        return item;
    }

    @Override
    public List<Item> getCartItems() {
        List<ItemDTO> itemsInCart = repository.findAll();

        List<Item> itemsList = new ArrayList<>();
        itemsInCart.forEach(itemInCart -> {
            Item item = new Item();
            Dimensions dimensions = new Dimensions();
            BeanUtils.copyProperties(itemInCart, item);
            BeanUtils.copyProperties(itemInCart, dimensions);
            item.setDimensions(dimensions);

            itemsList.add(item);
        });
        return itemsList;
    }

    @Override
    public Item findItemById(Integer id) throws Exception {
        Optional<ItemDTO> itemDTO = repository.findById(id);

        Item item = new Item();
        Dimensions dimensions = new Dimensions();
        if(itemDTO.isPresent()) {
            BeanUtils.copyProperties(itemDTO.get(), item);
            BeanUtils.copyProperties(itemDTO.get(), dimensions);
            item.setDimensions(dimensions);
        }
        else {
            throw new Exception(String.format("Id: %d não encontrado.", id));
        }
        return item;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

}
