package user.ivanardo.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import user.ivanardo.orderservice.dto.InventoryResponse;
import user.ivanardo.orderservice.dto.OrderLineItemsDto;
import user.ivanardo.orderservice.dto.OrderRequest;
import user.ivanardo.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {


    @Container
    static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.1.0");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
    @Mock
    private WebClient webClientMock;


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.driver", mySQLContainer::getJdbcDriverInstance);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    void shouldCreateOrder() throws Exception {

//        String expectedUri = "http://inventory-service/api/inventory";
//
//        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
//        when(requestHeadersUriMock.uri(expectedUri)).thenReturn(requestBodyMock);
//        when(requestBodyMock.retrieve()).thenReturn(responseMock);
//        when(responseMock.bodyToMono(InventoryResponse[].class)).thenReturn(Mono.just(getInventoryResponse().toArray(new InventoryResponse[0])));
//
//
//        when(webClientMock.get()
//                .uri("http://inventory-service/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCode", anyList()).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block()).thenReturn(getInventoryResponse().toArray(new InventoryResponse[0]));
//
//        OrderRequest orderRequest = getOrderRequest();
//        String orderRequestString = objectMapper.writeValueAsString(orderRequest);
//
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(orderRequestString))
//                .andExpect(status().isCreated());
//        Assertions.assertEquals(1, orderRepository.findAll().size());
    }

    private OrderRequest getOrderRequest() {
        return OrderRequest.builder()
                .orderLineItemsDtoList(Collections.singletonList(OrderLineItemsDto.builder()
                        .skuCode("iphone_14")
                        .price(BigDecimal.valueOf(1200))
                        .quantity(1)
                        .build()))
                .build();
    }

    private List<InventoryResponse> getInventoryResponse() {
        return Collections.singletonList(InventoryResponse.builder()
                .skuCode("iphone_14")
                .isInStock(true)
                .build());
    }

}
