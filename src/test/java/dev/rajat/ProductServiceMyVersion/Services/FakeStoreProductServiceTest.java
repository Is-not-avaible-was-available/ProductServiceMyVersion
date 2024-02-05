package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.FakeStoreProductClient;
import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos.FakeStoreDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductServiceTest {
    @Autowired
   private FakeStoreProductService fakeStoreProductService;
    @MockBean
    private FakeStoreProductClient fakeStoreProductClientMock;


    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId("2823a3ce-67f7-4421-bfa5-b07f279f3021");
        fakeStoreDTO.setTitle("iPhone 14");
        when(fakeStoreProductClientMock.getProductById(any(String.class))).thenReturn(fakeStoreDTO);

        GenericProductDTO response = fakeStoreProductService.getProductById("2823a3ce-67f7-4421-bfa5-b07f279f3021");

        Assertions.assertEquals("2823a3ce-67f7-4421-bfa5-b07f279f3021", response.getId());
        Assertions.assertEquals("iPhone 14", response.getTitle());
    }

    @Test
    public void testGetProductByIdThrowsNotFoundExceptionWhenClientReturnsNull() throws NotFoundException {
        when(fakeStoreProductClientMock.getProductById(any(String.class))).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, ()->{
            fakeStoreProductService.getProductById("2823a3ce-67f7-4421-bfa5-b07f279f3021");
        });
    }
}
