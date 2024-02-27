package dev.rajat.ProductServiceMyVersion.Security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTData {
    private String email;
    private List<String> roles;
}
