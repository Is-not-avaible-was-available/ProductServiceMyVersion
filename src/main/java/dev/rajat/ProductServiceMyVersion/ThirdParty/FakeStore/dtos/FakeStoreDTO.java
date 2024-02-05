package dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
