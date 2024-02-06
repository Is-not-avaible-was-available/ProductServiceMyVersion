package dev.rajat.ProductServiceMyVersion.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Services.FakeStoreProductService;
import dev.rajat.ProductServiceMyVersion.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMockMVCTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private FakeStoreProductService productServiceMock;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductByIdAPI() throws Exception {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle("Iphone 12");
        genericProductDTO.setCategory("Electronics");
        genericProductDTO.setId("637a6546-2d86-4a55-b16e-200d257e2973");

        when(productServiceMock.getProductById(any(String.class))).thenReturn(genericProductDTO);

        ResultActions resultActions = mockMvc.perform(get("/products/637a6546-2d86-4a55-b16e-200d257e2973"))
                .andExpect(status().is(200))
                .andExpect(content().json("{\"id\":\"637a6546-2d86-4a55-b16e-200d257e2973\",\"title\":\"Iphone 12\",\"description\":null,\"image\":null,\"category\":\"Electronics\",\"price\":0.0}"));

        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        GenericProductDTO response = objectMapper.readValue(responseString, GenericProductDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("637a6546-2d86-4a55-b16e-200d257e2973", response.getId());
        Assertions.assertEquals("Iphone 12", response.getTitle());

    }
}
