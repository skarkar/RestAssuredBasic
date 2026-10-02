package httpReq;


import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class UpdateBooking {
	@Test
	void test_updateBooking(ITestContext context) {
		Faker f = new Faker();
		String fname = f.name().firstName();
		String lname = f.name().lastName();
		int tprice = f.number().numberBetween(1000, 9000);
		boolean depo = true;
		
		JSONObject dates = new JSONObject();
		dates.put("checkin", "2027-01-01");
		dates.put("checkout", "2027-10-01");
		
		String addneed = f.food().dish();
		
		JSONObject payload = new JSONObject();
		
		payload.put("firstname", fname);
		payload.put("lastname", lname);
		payload.put("totalprice", tprice);
		payload.put("depositpaid", depo);
		payload.put("bookingdates", dates);
		payload.put("additionalneeds", addneed);
	
	given()
		.pathParam("id", context.getSuite().getAttribute("booking_id"))
		.contentType(ContentType.JSON)
		.headers("Cookie", "token=" + context.getSuite().getAttribute("token"))
		.body(payload.toString())
		.log().all()
	.when()
		.put("https://restful-booker.herokuapp.com/booking/{id}")
	.then()
		.statusCode(200)
		.log().all();
}

}
