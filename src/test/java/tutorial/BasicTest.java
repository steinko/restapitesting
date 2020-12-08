package tutorial;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicTest {

	@Test
	void shouldExistAPlace() {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		given()
		   .header("Content-Type","application/json")
		   .queryParam("key", "qaclick123")
		   .body("{\r\n\" + \r\n"
		   		+ "\"place_id\":\"\"+placeId+\"\",\r\n\" + \r\n"
		   		+  "\"address\":\"\"+newAddress+\"\",\r\n\" + \r\n"
		   		+ "\"key\":\"qaclick123\"\r\n\" + \r\n"
		   		+ "\"}\").")
		   .when()
		      .put("maps/api/place/update/json")
		   .then().assertThat().statusCode(200);
		   
	}

}
