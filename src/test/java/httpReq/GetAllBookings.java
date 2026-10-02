package httpReq;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetAllBookings {
	@Test
	void test_getAllBookings(ITestContext context) {
	
	List<?> len = given()
			.queryParam("checkin", "2027-01-01")
	.when()
		.get("https://restful-booker.herokuapp.com/booking")
	.then()
		.statusCode(200)
		.log().body(true)
		.extract().jsonPath().get("$");
	
	System.out.println("Total Bookings: " + len.size());
}
}
