package tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;

public class ComplexJson  {
	
	@Test
	public void shouldDeliverNumberofCourses() {
		
		JsonPath js=new JsonPath(payload.CoursePrice()); 
		assertEquals(js.getInt("courses.size()"),4);
		
	}
	
	@Test
	public void shouldDeliveePurchaseAmount() {
		
		JsonPath js=new JsonPath(payload.CoursePrice()); 
		assertEquals(js.getInt("dashboard.purchaseAmount"),1162);
		
	}
	
	

}
