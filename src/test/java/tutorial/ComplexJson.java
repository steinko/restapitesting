package tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.path.json.JsonPath;

public class ComplexJson  {
	
	JsonPath js;
	
	@BeforeEach
	public void setUp( ) {
	    js=new JsonPath(payload.CoursePrice()); 
	}
	
	@Test
	public void shouldDeliverNumberofCourses() {
		assertEquals(js.getInt("courses.size()"),4);
	}
	
	@Test
	public void shouldDelivrePurchaseAmount() {
		assertEquals(js.getInt("dashboard.purchaseAmount"),1162);
	}
	
	
	@Test
	public void shouldDeliverTitleFirstCourse() {
		assertEquals(js.getString("courses[0].title"),"Selenium Python");
	}
	
	

}
