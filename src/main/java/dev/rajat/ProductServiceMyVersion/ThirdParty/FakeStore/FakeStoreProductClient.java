package dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore;

import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos.FakeStoreDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    private String singleProductUrl;
    private String allProductsUrl;
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}") String fakeStoreBaseUrl,
                                  @Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.singleProductUrl = fakeStoreBaseUrl + fakeStoreProductPath + "/{id}";
        this.allProductsUrl = fakeStoreBaseUrl + fakeStoreProductPath;
        this.restTemplateBuilder = restTemplateBuilder;

    }

    public FakeStoreDTO getProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                restTemplate.getForEntity(singleProductUrl, FakeStoreDTO.class, id);
        FakeStoreDTO fakeStoreDTO = fakeStoreDTOResponseEntity.getBody();
        if(fakeStoreDTO==null){
            throw  new NotFoundException("Product with id: "+ id+", is not found!");
        }
        return fakeStoreDTO;
    }

    public List<FakeStoreDTO> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO[]> fakeStoreResponse =
                restTemplate.getForEntity(allProductsUrl, FakeStoreDTO[].class);
        FakeStoreDTO[] fakeStoreDTOS = fakeStoreResponse.getBody();
        if(fakeStoreDTOS==null){
            throw new NotFoundException("Products not found!");
        }
        return Arrays.asList(fakeStoreDTOS);
    }

    public FakeStoreDTO createProduct(GenericProductDTO genericProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                restTemplate.postForEntity(allProductsUrl, genericProductDTO, FakeStoreDTO.class);
        return fakeStoreDTOResponseEntity.getBody();
    }

    public FakeStoreDTO deleteProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity=
                restTemplate.exchange(singleProductUrl, HttpMethod.DELETE,
                        null, FakeStoreDTO.class, id);
        FakeStoreDTO fakeStoreDTO = fakeStoreDTOResponseEntity.getBody();
        if(fakeStoreDTO==null){
            throw new NotFoundException("Product with id: "+ id+", is not found!");
        }
        return fakeStoreDTO;
    }

    public FakeStoreDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity =
                restTemplate.exchange(singleProductUrl, HttpMethod.PUT, new HttpEntity<>(genericProductDTO),
                        FakeStoreDTO.class, id);
        FakeStoreDTO fakeStoreDTO = fakeStoreDTOResponseEntity.getBody();
        if(fakeStoreDTO==null){
            throw new NotFoundException("Product with id: "+ id+", is not found!");
        }
        return fakeStoreDTO;
    }
}
