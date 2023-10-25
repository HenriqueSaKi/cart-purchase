package br.com.cartpurchase.controller;

import br.com.cartpurchase.mock.AddItemMock;
import br.com.cartpurchase.mock.ItemMock;
import br.com.cartpurchase.model.AddItem;
import br.com.cartpurchase.model.Item;
import br.com.cartpurchase.service.CartPurchaseServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CartPurchaseControllerTest {

    @Mock
    private CartPurchaseServiceImpl service;

    @InjectMocks
    CartPurchaseController controller;

    MockMvc mockMvc;

    private ItemMock itemMock;
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();
        itemMock = new ItemMock();

    }

    @Test
    public void testAddItemsToCart() throws Exception {
        AddItem addItem = new AddItemMock().getItem1();
        Item item = itemMock.getItem1();
        when(service.saveInCart(Mockito.any())).thenReturn(item);

        mockMvc.perform(
                post("/cart/add_to_cart")
                        .contentType("application/json")
                        .content(new Gson().toJson(addItem))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(item.getId())));

    }

    @Test
    public void testListCartItems() throws Exception {
        when(service.getCartItems()).thenReturn(itemMock.getItemList());

        mockMvc.perform(get("/cart/list_cart_items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[1].id", equalTo(2)));

    }

    @Test
    public void testListCartItemsEmptyCart() throws Exception {
        when(service.getCartItems()).thenReturn(new ArrayList<>());

        String responseContent = mockMvc
                .perform(get("/cart/list_cart_items"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        int status = mockMvc
                .perform(get("/cart/list_cart_items"))
                .andReturn()
                .getResponse()
                .getStatus();

        Assertions.assertEquals(404, status);
        Assertions.assertEquals("Your cart is empty!", responseContent);

    }

}
