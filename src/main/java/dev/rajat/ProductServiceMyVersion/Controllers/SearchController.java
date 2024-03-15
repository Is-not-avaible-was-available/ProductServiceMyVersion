package dev.rajat.ProductServiceMyVersion.Controllers;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.SearchRequestDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.SortedSearchRequestDTO;
import dev.rajat.ProductServiceMyVersion.Services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping()
    public Page<GenericProductDTO> searchProductByTitle(@RequestBody SearchRequestDTO requestDTO){
       return searchService.searchProductByTitle(requestDTO.getQuery(), requestDTO.getPageSize(),
                requestDTO.getPageNumber());
    }

    @PostMapping("/title")
    public Page<GenericProductDTO> searchProductByTitleSorted(@RequestBody SortedSearchRequestDTO requestDTO){
        return searchService.searchProductByTitleSorted(requestDTO.getQuery(),
                requestDTO.getPageSize(), requestDTO.getPageNumber(), requestDTO.getSortValue());
    }

    @PostMapping("/price")
    public Page<GenericProductDTO> searchProductByTitleSortedByPrice(@RequestBody SortedSearchRequestDTO requestDTO){
        return searchService.searchProductByTitleSortedByPrice(requestDTO.getQuery(),
                requestDTO.getPageSize(), requestDTO.getPageNumber(), requestDTO.getSortValue());
    }
}
