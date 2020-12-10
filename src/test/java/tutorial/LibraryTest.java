package tutorial;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


public class LibraryTest {
	
	//http://216.10.245.166/Library/Addbook.php
	@Disabled
	@Test
	public void shouldAddABook() {
		RestAssured.baseURI= "http://216.10.245.166";
		String response=
				given()
				 .header("Content-Type","application/json")
		         .body("{\r\n"
		         		+ "\r\n"
		         		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		         		+ "\"isbn\":\"bcd\",\r\n"
		         		+ "\"aisle\":\"227\",\r\n"
		         		+ "\"author\":\"John foe\"\r\n"
		         		+ "}\r\n")
		         .when()
		           .post("/Library/Addbook.php")
		         .then()
		            .assertThat()
		               .statusCode(200)
		               .body("msg", equalTo("sucessfully added"))
		         .extract().response().asString();
	}
	
	

}
