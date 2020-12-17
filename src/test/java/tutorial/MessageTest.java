package tutorial;


import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
	
	@Test
	public void shouldStoreMessage() {
		
		 
   	   String url =  "http://localhost:8080/message";	
   	   Message message = new Message("message");
   	   
   	   given()
         .contentType("application/json")
         .body(message)
       .when()
          .post(url)
       .then()
         .statusCode(201); 
	}
	
	@Test
	public void shouldGetMessage() {
		 
   	   String url =  "http://localhost:8080/message";	
   	 
   	   given()
       .when()
          .get(url)
       .then()
          .contentType("application/json")
          .assertThat()
             .body("message",equalTo("get message"))
         .statusCode(200); 
	}
	
	@Test
	public void shouldGetMessage1() {
		 
   	   String url =  "http://localhost:8080/message";	 
       Message respons = get(url).as(Message.class);
       assertEquals("get message", respons.getMessage());     
	}
	
}
