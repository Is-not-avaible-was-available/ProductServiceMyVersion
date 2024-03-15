package dev.rajat.ProductServiceMyVersion.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    private String query;
    private int pageSize;
    private int pageNumber;
}
