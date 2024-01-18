package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.ThirdPartyClient.FakeStore.dtos.FakeStoreProductDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.ThirdPartyClient.FakeStore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }


    public GenericProductDTO fakeStoreDTOtoGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        return fakeStoreDTOtoGenericProductDTO(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreProductClient.getAllProducts();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){
            GenericProductDTO genericProductDTO = fakeStoreDTOtoGenericProductDTO(fakeStoreProductDTO);
            genericProductDTOS.add(genericProductDTO);
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO) {
       return fakeStoreDTOtoGenericProductDTO(fakeStoreProductClient.createProduct(newProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        return fakeStoreDTOtoGenericProductDTO(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProductDTO) {
        return fakeStoreDTOtoGenericProductDTO(fakeStoreProductClient.updateProductById(id, updatedProductDTO));
    }
}
