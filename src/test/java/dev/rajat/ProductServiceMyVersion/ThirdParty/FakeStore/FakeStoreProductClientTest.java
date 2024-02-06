package dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos.FakeStoreDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductClientTest {
    @Autowired
    private FakeStoreProductClient fakeStoreProductClient;
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId("asdasdsadasds-1e22adasdas-112dasds-123faf");
        fakeStoreDTO.setTitle("iPhone 15");
        fakeStoreDTO.setCategory("Electronics");

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                new ResponseEntity<>(fakeStoreDTO, HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class),eq(FakeStoreDTO.class),any(String.class)))
                .thenReturn(fakeStoreDTOResponseEntity);

        FakeStoreDTO response = fakeStoreProductClient
                .getProductById("asdasdsadasds-1e22adasdas-112dasds-123faf");

        Assertions.assertEquals("asdasdsadasds-1e22adasdas-112dasds-123faf", response.getId());
        Assertions.assertEquals("iPhone 15", response.getTitle());
        Assertions.assertEquals("Electronics", response.getCategory());
    }

    @Test
    public  void testGetProductByIdThrowsNotFoundExceptionWhenClientReturnsNull(){
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity=
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreDTO.class), any(String.class)))
                .thenReturn(fakeStoreDTOResponseEntity);

        Assertions.assertThrows(NotFoundException.class, ()->{
           fakeStoreProductClient.getProductById("asdasdsadasds-1e22adasdas-112dasds-123faf");
        });
    }

    @Test
    public void testDeleteProductByIdReturnsCorrectResponse() throws NotFoundException {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        when(restTemplateBuilder.build()).thenReturn(restTemplateMock);

        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId("asdasdsadasds-1e22adasdas-112dasds-123faf");
        fakeStoreDTO.setTitle("Test-Product");
        fakeStoreDTO.setDescription("testing correct response from delete product");
        fakeStoreDTO.setImage("www.test.com");
        fakeStoreDTO.setPrice(110.0);
        fakeStoreDTO.setCategory("Test");

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                new ResponseEntity<>(fakeStoreDTO, HttpStatus.OK);

        when(restTemplateMock.exchange(any(String.class), any(HttpMethod.class),
                any(), eq(FakeStoreDTO.class), any(String.class)))
                .thenReturn(fakeStoreDTOResponseEntity);

        FakeStoreDTO response = fakeStoreProductClient
                .deleteProductById("asdasdsadasds-1e22adasdas-112dasds-123faf");

        Assertions.assertEquals(fakeStoreDTO.getPrice(), response.getPrice());
        Assertions.assertEquals(fakeStoreDTO.getId(), response.getId());
    }

    @Test
    public void testDeleteProductByIdThrowsNotFoundException(){
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplateMock);

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        when(restTemplateMock.exchange(any(String.class), any(HttpMethod.class), any()
                , eq(FakeStoreDTO.class), any(String.class)))
                .thenReturn(fakeStoreDTOResponseEntity);

        Assertions.assertThrows(NotFoundException.class, ()->{
            fakeStoreProductClient.deleteProductById("asdasdsadasds-1e22adasdas-112dasds-123faf");
        });
    }
}
