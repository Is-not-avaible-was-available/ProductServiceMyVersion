package dev.rajat.ProductServiceMyVersion.ThirdPartyClient.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
