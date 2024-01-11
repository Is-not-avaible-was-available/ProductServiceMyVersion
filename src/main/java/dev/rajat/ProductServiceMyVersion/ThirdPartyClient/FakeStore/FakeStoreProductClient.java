package dev.rajat.ProductServiceMyVersion.ThirdPartyClient.FakeStore;

import dev.rajat.ProductServiceMyVersion.DTOs.FakeStoreProductDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String singleProductUrl;
    private String productsUrl;
    private final String productPath = "/products";

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder
            , @Value("${fakestore.api.baseurl}")String fakeStoreApiBaseUrl
            ,@Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.singleProductUrl = fakeStoreApiBaseUrl + productPath + "/{id}";
        this.productsUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;
    }


    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }

    public List<FakeStoreProductDTO> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductResponseEntity =
        restTemplate.getForEntity(productsUrl, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreProductResponseEntity.getBody();
        if(fakeStoreProductDTOS==null){
            throw new NotFoundException("Products not available");
        }
        return Arrays.asList(fakeStoreProductDTOS);
    }
    public FakeStoreProductDTO createProduct(GenericProductDTO newProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.postForEntity(productsUrl, newProductDTO, FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public FakeStoreProductDTO deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.exchange(singleProductUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO updatedProduct){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl, HttpMethod.PUT, new HttpEntity<>(updatedProduct), FakeStoreProductDTO.class, id);
        return fakeStoreProductDTOResponseEntity.getBody();
    }
}
