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
	
	
	@Test
	public void shouldDeliverNumberOfCopiesSold() {
		int i;
		for ( i=0; i< js.getInt("courses.size"); i++)
		{
			
			if (js.getString("courses[" + i+ "].title") == "RPA") {
				assertEquals(js.getInt("courses[" + i + "].copies"),10);
		    }
		}
	}
	
	@Test
	public void shouldVerifyPurchaseAmountIntegrity() {
		int i;
		int soldAmount= 0;
		for ( i=0; i< js.getInt("courses.size"); i++)
		{
			int copies = js.getInt("courses[" + i + "].copies");
			int price = js.getInt("courses[" + i + "].price");
			int amount = copies*price; 
			soldAmount = soldAmount + amount;
			
		}
		assertEquals(soldAmount, js.getInt("dashboard.purchaseAmount"));
	}
	
	
}
