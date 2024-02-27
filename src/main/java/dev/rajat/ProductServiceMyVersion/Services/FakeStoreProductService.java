package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos.FakeStoreDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    private GenericProductDTO fakeToGenericProductDTO(FakeStoreDTO fakeStoreDTO){
        if(fakeStoreDTO == null){
            return null;
        }
        GenericProductDTO genericProductDTO= new GenericProductDTO();
        genericProductDTO.setId(fakeStoreDTO.getId());
        genericProductDTO.setTitle(fakeStoreDTO.getTitle());
        genericProductDTO.setImage(fakeStoreDTO.getImage());
        genericProductDTO.setDescription(fakeStoreDTO.getDescription());
        genericProductDTO.setPrice(fakeStoreDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreDTO.getCategory());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        GenericProductDTO genericProductDTO = fakeToGenericProductDTO(fakeStoreProductClient.getProductById(id));
        if(genericProductDTO == null){
            throw new NotFoundException("Not found " + id);
        }
        return genericProductDTO;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<FakeStoreDTO> fakeStoreDTOS = fakeStoreProductClient.getAllProducts();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreDTO fakeStoreDTO: fakeStoreDTOS){
            genericProductDTOS.add(fakeToGenericProductDTO(fakeStoreDTO));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return fakeToGenericProductDTO(fakeStoreProductClient.createProduct(genericProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        return fakeToGenericProductDTO(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProduct) throws NotFoundException {
        return fakeToGenericProductDTO(fakeStoreProductClient.updateProductById(id, updatedProduct));
    }
}
