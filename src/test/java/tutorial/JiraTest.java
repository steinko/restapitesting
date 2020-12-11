package tutorial;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class JiraTest {
	
	@Test
	public void shouldHaveATask() {
     	RestAssured.baseURI= "http://localhost:8080";
     	SessionFilter session = new SessionFilter();
	 
	    	given()
	    	    .header("Content-Type","application/json")
	            .body("{ \"username\": \"steinko\", \"password\": \"RoxyMusic11\" }")
	            .filter(session)
	       .when()
	           .post("/rest/auth/1/session")
	        .then()
	            .assertThat()
	                 .statusCode(200).body("session.name", equalTo("JSESSIONID"));
	       
	   
	//   String createRespons = 
	//	  given()
	//	      .header("Content-Type", "application/json").
	//		   header("cookie","JSESSIONID=" + sessionValue).
	//		   body("{\"fields\": {"
	//		             +            "\"project\":"
	//				     +               " {"
	//		             +                    "\"key\": \"COOL\""+
	//		                               "},"
	//		             +             "\"summary\": \"Issue 5 for adding comment\","
	//		             +             "\"description\": \"Creating my second bug\","
	//		             +              "\"issuetype\": {"
	//		             +                       "\"name\": \"Bug\""
	//		             +                  "}"
	//		             +      "}"
	//		         + "}"                        
	//		      )
	//	 .when().
	//		 post("/rest/api/2/issue")
	//	 .then()
	//		.statusCode(201)
	//		.body("key", contains("COOL"))
	//	  .extract().response().asString();
	   
	 //  JsonPath js1=new JsonPath(createRespons); //for parsing Json
	 //  String key =js1.getString("key"); 
	   
	   given()
	      .pathParam("key", "COOL-1")
	      .header("Content-Type", "application/json")
	      .filter(session)
		  .body("{\r\n"
		   		+ "    \"body\": \"This is a comment regarding the quality of the response.\"\r\n"
		   		+ "}")
	  .when().
		 post("/rest/api/2/issue/{key}/comment")
	  .then()
	     .assertThat()
		    .statusCode(201);
	   
	   
	   //Get Issue
	   
	   given()
	      .pathParam("key", "COOL-1")
	      .header("Content-Type", "application/json")
	      .filter(session)
	  .when().
		 get("/rest/api/2/issue/{key}")
	  .then()
	     .assertThat()
		    .statusCode(200);
	   
	   
	   
					    
					
	   
	}
}