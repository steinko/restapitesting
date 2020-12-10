package tutorial;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class JiraTest {
	
	@Test
	public void shouldHaveATask() {
     	RestAssured.baseURI= "http://localhost:8080";
	    String response=
	    	given()
	    	    .header("Content-Type","application/json")
	            .body("{ \"username\": \"steinko\", \"password\": \"RoxyMusic11\" }")
	       .when()
	           .post("/rest/auth/1/session")
	        .then()
	            .assertThat()
	                 .statusCode(200).body("session.name", equalTo("JSESSIONID"))
	       .extract().response().asString();
	
	   JsonPath js=new JsonPath(response); //for parsing Json
	   String sessionVallue=js.getString("session.value");
	}
}
