package dev.rajat.ProductServiceMyVersion.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SortedSearchRequestDTO {
    private String query;
    private int pageSize;
    private int pageNumber;
    private String sortValue;
}
