package dev.rajat.ProductServiceMyVersion.Security;

import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ValidateToken {
    private RestTemplateBuilder restTemplateBuilder;
    public ValidateToken(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JWTData> validateToken(String token) throws NotFoundException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<JWTData> jwtDataResponseEntity =
//        restTemplate.getForEntity("/validate", JWTData.class);
//        JWTData jwtData = jwtDataResponseEntity.getBody();
//        if(jwtData==null){
//            throw new NotFoundException("Not found!");
//        }
//        return Optional.of(jwtData);
        return Optional.empty();
    }
}
