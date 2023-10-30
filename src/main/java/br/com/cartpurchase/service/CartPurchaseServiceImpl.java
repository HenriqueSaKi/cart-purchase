package br.com.cartpurchase.service;

import br.com.cartpurchase.model.Dimensions;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.EmailDTO;
import br.com.cartpurchase.model.dto.ItemDTO;
import br.com.cartpurchase.repository.CartPurchaseRepository;
import com.google.gson.Gson;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartPurchaseServiceImpl implements CartPurchaseService{

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Autowired
    CartPurchaseRepository repository;

    @Autowired
    RabbitTemplate rabbitTemplate;

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
            throw new Exception(String.format("Id: %d not found.", id));
        }
        return item;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public void sendMessage2Queue(EmailDTO emailDTO, List<Item> items) {
        StringBuilder text = new StringBuilder();
        text.append("Resumo da compra:\n");
        items.forEach(item -> text.append("\tItem ")
                .append(item.getId())
                .append(" - ")
                .append(item.getProductTitle())
                .append("\n")
                .append("\tQuantidade: ")
                .append(item.getQuantity())
                .append("\n"));
        emailDTO.setSubject("Chegou o resumo da sua compra!");
        emailDTO.setText(text.toString());

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");

        String json = new Gson().toJson(emailDTO);

        rabbitTemplate.send(
                queue,
                MessageBuilder.withBody(json.getBytes()).andProperties(messageProperties).build()
        );

    }

}
