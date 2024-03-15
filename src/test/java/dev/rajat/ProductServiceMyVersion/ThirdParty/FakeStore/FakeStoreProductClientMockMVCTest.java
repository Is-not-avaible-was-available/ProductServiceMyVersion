//package dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore;
//
//import dev.rajat.ProductServiceMyVersion.ThirdParty.FakeStore.dtos.FakeStoreDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.web.client.RestTemplate;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class FakeStoreProductClientMockMVCTest {
//    @Autowired
//    private FakeStoreProductClient fakeStoreProductClient;
//    @MockBean
//    private RestTemplateBuilder restTemplateBuilderMock;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testGetProductByIdAPI() throws Exception {
////        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
////
////        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);
////
////        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
////        fakeStoreDTO.setId("637a6546-2d86-4a55-b16e-200d257e2973");
////        fakeStoreDTO.setTitle("Iphone 15");
////        fakeStoreDTO.setCategory("Electronics");
////
////        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntityMock =
////                new ResponseEntity<>(fakeStoreDTO, HttpStatus.OK);
////
////        when(restTemplateMock.getForEntity(any(String.class), eq(FakeStoreDTO.class), any(String.class)))
////                .thenReturn(fakeStoreDTOResponseEntityMock);
////
////       ResultActions resultActions = mockMvc.perform(get("/products/637a6546-2d86-4a55-b16e-200d257e2973"))
////                .andExpect(status().is(200))
////               .andExpect(content().json("{\"id\":\"637a6546-2d86-4a55-b16e-200d257e2973\",\"title\":\"Iphone 15\",\"description\":null,\"image\":null,\"category\":\"Electronics\",\"price\":0.0}"));
////
////       String responseString = resultActions.andReturn().getResponse().getContentAsString();
////
////        Assertions.assertNotNull(responseString);
//
//    }
//}
