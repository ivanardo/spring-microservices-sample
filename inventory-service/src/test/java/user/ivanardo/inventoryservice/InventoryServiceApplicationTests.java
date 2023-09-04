package user.ivanardo.inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import user.ivanardo.inventoryservice.dto.InventoryResponse;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServiceApplicationTests {

	private static final String SKU_CODE = "iphone_14";

	@Container
	static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.1.0");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.driver", mySQLContainer::getJdbcDriverInstance);
		dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
	}

	@Test
	void shouldBeInStock() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory")
						.queryParam("skuCode", SKU_CODE))
				.andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals(
				objectMapper.writeValueAsString(Collections.singletonList(prepareInventoryResponse())),
				result.getResponse().getContentAsString());
	}

	private InventoryResponse prepareInventoryResponse() {
		return InventoryResponse.builder()
				.skuCode("iphone_14")
				.isInStock(true)
				.build();
	}

}
