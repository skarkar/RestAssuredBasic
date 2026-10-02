package httpReq;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetBooking {
	@Test
	void test_getBooking(ITestContext context) {
		
	given()
		.pathParam("id", context.getSuite().getAttribute("booking_id"))
	.when()
		.get("https://restful-booker.herokuapp.com/booking/{id}")
	.then()
		.statusCode(200)
		.log().body(true);
}
}
