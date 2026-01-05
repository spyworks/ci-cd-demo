package com.example.cicddemo.integration;

import com.example.cicddemo.api.CreateOrderRequest;
import com.example.cicddemo.api.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApiIntegrationTest {

    @LocalServerPort
    int port;

    private final TestRestTemplate rest = new TestRestTemplate();

    @Test
    void createAndFetchOrder() {
        String base = "http://localhost:" + port;

        CreateOrderRequest req = new CreateOrderRequest();
        req.setCustomerName("Bob");
        req.setItem("Laptop");
        req.setQuantity(1);

        var postRes = rest.postForEntity(base + "/api/orders", req, OrderResponse.class);
        assertThat(postRes.getStatusCode().is2xxSuccessful()).isTrue();
        OrderResponse created = postRes.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        OrderResponse fetched = rest.getForObject(base + "/api/orders/" + created.getId(), OrderResponse.class);
        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(created.getId());
        assertThat(fetched.getItem()).isEqualTo("Laptop");
    }
}