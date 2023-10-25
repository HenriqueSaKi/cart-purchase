package br.com.cartpurchase.service;

import br.com.cartpurchase.mock.ItemDTOMock;
import br.com.cartpurchase.model.Dimensions;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.model.dto.EmailDTO;
import br.com.cartpurchase.model.dto.ItemDTO;
import br.com.cartpurchase.repository.CartPurchaseRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartPurchaseServiceImplTest {

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Mock
    CartPurchaseRepository repository;

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    CartPurchaseServiceImpl service;

    ItemDTOMock itemDTOMock;

    @BeforeEach
    public void init() {
        itemDTOMock = new ItemDTOMock();

    }

    @Test
    public void testSaveInCart() {
        Item item = service.saveInCart(itemDTOMock.getItem1());

        Mockito.verify(repository).save(itemDTOMock.getItem1());
        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item.getDimensions());

    }

    @Test
    public void testGetCartItems() {
        when(repository.findAll())
                .thenReturn(itemDTOMock.getItemsList());

        Assertions.assertNotNull(service.getCartItems());
        Assertions.assertTrue(service.getCartItems() instanceof ArrayList);

    }

    @Test
    public void testFindItemById() throws Exception {
        ItemDTO item1 = itemDTOMock.getItem1();
        when(repository.findById(1))
                .thenReturn(Optional.of(item1));

        Assertions.assertNotNull(service.findItemById(1));
        Assertions.assertEquals(1, service.findItemById(1).getId());

    }

    @Test
    public void testFindItemByIdNotFound() {
        Exception e = Assertions.assertThrows(Exception.class, () -> service.findItemById(3));
        Assertions.assertEquals("Id: 3 not found.", e.getMessage());

    }

    @Test
    public void testDeleteById() {
        service.deleteById("5");
        Mockito.verify(repository).deleteById(5);
    }

    @Test
    public void testSendMessage2Queue() {
        Item item = new Item();
        Dimensions dimensions = new Dimensions();
        BeanUtils.copyProperties(itemDTOMock.getItem1(), item);
        BeanUtils.copyProperties(itemDTOMock.getItem1(), dimensions);
        item.setDimensions(dimensions);

        List<Item> items = new ArrayList<>();
        items.add(item);

        StringBuffer text = new StringBuffer();
        text.append("Resumo da compra:\n");
        items.forEach(i -> {
            text.append("\tItem ")
                    .append(i.getId())
                    .append(" - ")
                    .append(i.getProductTitle())
                    .append("\n")
                    .append("\tQuantidade: ")
                    .append(i.getQuantity())
                    .append("\n");
        });

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setOwnerRef("Exemplo");
        emailDTO.setEmailFrom("exemplofrom@exemplo.com");
        emailDTO.setEmailTo("exemploto@exemplo.com");
        emailDTO.setSubject("Chegou o resumo da sua compra!");
        emailDTO.setText(text.toString());

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");

        String json = new Gson().toJson(emailDTO);

        Message message = MessageBuilder
                        .withBody(json.getBytes())
                        .andProperties(messageProperties)
                        .build();

        service.sendMessage2Queue(emailDTO, items);

        Mockito.verify(rabbitTemplate).send(queue, message);

    }

    @Test
    public void testSendMessage2QueueAmqpException() {
        Item item = new Item();
        Dimensions dimensions = new Dimensions();
        BeanUtils.copyProperties(itemDTOMock.getItem1(), item);
        BeanUtils.copyProperties(itemDTOMock.getItem1(), dimensions);
        item.setDimensions(dimensions);

        List<Item> items = new ArrayList<>();
        items.add(item);

        StringBuffer text = new StringBuffer();
        text.append("Resumo da compra:\n");
        items.forEach(i -> {
            text.append("\tItem ")
                    .append(i.getId())
                    .append(" - ")
                    .append(i.getProductTitle())
                    .append("\n")
                    .append("\tQuantidade: ")
                    .append(i.getQuantity())
                    .append("\n");
        });

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setOwnerRef("Exemplo");
        emailDTO.setEmailFrom("exemplofrom@exemplo.com");
        emailDTO.setEmailTo("exemploto@exemplo.com");
        emailDTO.setSubject("Chegou o resumo da sua compra!");
        emailDTO.setText(text.toString());

        Mockito.doThrow(new AmqpException("Simulated AmqpException")).when(rabbitTemplate)
                .send(Mockito.eq(queue), Mockito.any());

        Assertions.assertThrows(AmqpException.class, () -> {
            service.sendMessage2Queue(emailDTO, items);
        });

    }

}
