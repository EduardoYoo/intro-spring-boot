package example.cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

//Will start Spring Boot application and make it available for our test to perform requests to it.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//As of now, tests are all hard coded in order to test Spring Boot's Bean functionality and integration
class CashCardApplicationTests {

	//Spring will inject a test helper that’ll allow us to make HTTP requests to the locally running application.
	@Autowired
 	TestRestTemplate restTemplate;

	@Test
 	void shouldReturnACashCardWhenDataIsSaved() {

		//restTemplate will make a GET request to the /cashcards/99 endpoint
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		//Converts response String into JSON-aware object
		DocumentContext documentContext = JsonPath.parse(response.getBody());

		//Test that the id matches the value we want
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);
			
		//Test that the amount matches the value we want
		Double amount = documentContext.read("$.amount");
		assertThat(amount).isEqualTo(123.45);
 	}

	@Test
	//Test that will ignore CashCards without an id of 99
	void shouldNotReturnACashCardWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
}