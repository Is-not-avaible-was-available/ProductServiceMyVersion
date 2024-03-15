package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Mappers.DTOmappers;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<GenericProductDTO> searchProductByTitle(String query, int size, int pageNumber){

        Pageable pageable = PageRequest.of(pageNumber,size);
        Page<Product> productList = productRepository.findAllByTitleContaining(query, pageable);

        List<Product> products = productList.stream().toList();

        List<GenericProductDTO> genericProductDTOS = products
                .stream()
                .map(DTOmappers::productToGenericProductDto)
                .toList();

        return new PageImpl<>(genericProductDTOS, productList.getPageable(),
                productList.getTotalPages());
     }

     public Page<GenericProductDTO> searchProductByTitleSorted(String query, int size,
                                                               int pageNumber, String sortValue){

        Sort sort = null;
        if(sortValue.equals("asc")){
            sort = Sort.by("title").ascending();
        }else{
            sort = Sort.by("title").descending();
        }

         Pageable pageable = PageRequest.of(pageNumber, size, sort);
        Page<Product> productsPage = productRepository.findAllByTitleContaining(query, pageable);

        List<Product> products = productsPage.get().toList();
        List<GenericProductDTO> genericProductDTOS = products
                .stream()
                .map(DTOmappers::productToGenericProductDto)
                .toList();

         return new PageImpl<>(genericProductDTOS,
                productsPage.getPageable(), productsPage.getTotalElements());
     }

     public Page<GenericProductDTO> searchProductByTitleSortedByPrice(String query, int pageSize,
                                                                      int pageNumber,String sortValue){

        Sort sort = null;
        if(sortValue.equals("asc")){
            sort = Sort.by("price_price").ascending();
        }else{
            sort = Sort.by("price_price").descending();
        }
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);

        Page<Product> productsPage = productRepository.findAllByTitleContaining(query, pageable);

        List<Product> products = productsPage.get().toList();

        List<GenericProductDTO> genericProductDTOS = products
                .stream()
                .map(DTOmappers::productToGenericProductDto)
                .toList();

         return new PageImpl<>(genericProductDTOS,
                productsPage.getPageable(), productsPage.getTotalElements());
     }
}
