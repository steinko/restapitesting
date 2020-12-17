package tutorial;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


public class JiraTest {
	@Disabled
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
	  
	   String comment = "This is a comment regarding the quality of the response.";
	   String respons = 	
	   given()
	      .pathParam("key", "COOL-1")
	      .header("Content-Type", "application/json")
	      .filter(session)
		  .body("{ body \": " + comment + " }")
	  .when().
		 post("/rest/api/2/issue/{key}/comment")
	  .then()
	     .assertThat()
		    .statusCode(201)
	   .extract().response().asString();
	   
	   JsonPath js=new JsonPath(respons); //for parsing Json
		String commentId=js.getString("id");
	   
	   
	   //Get Issue
	   
	 String issueRespons = 
	   given()
	      .pathParam("key", "COOL-1")
	      .queryParam("fields", "comments")
	      .header("Content-Type", "application/json")
	      .filter(session)
	  .when().
		 get("/rest/api/2/issue/{key}")
	  .then()
	     .assertThat()
		    .statusCode(200)
		    .extract().response().asString();
		    
	    JsonPath js1=new JsonPath(issueRespons); //for parsing Json
	//	String commentId=js.getString("field.comment.comments")
	//	List<Map> comments = with(Object).get("field.comment.comments.findAll { comments -> comments.id == " + commentId + " }");
	//	Map<K, V> comment = comments.get(0);
		int commentCount = js1.getInt("field.comment.comments");
		for (int i =0; i < commentCount; i ++) {
			  String foundCommentId = js1.getString("field.comment.comments[" + i +"].id");
			if (foundCommentId == commentId) {
				assertEquals( js1.getString("field.comment.comments[" +Integer.toString(i) +"].body"),comment);
			}

		}
					    
					
	   
	}
}