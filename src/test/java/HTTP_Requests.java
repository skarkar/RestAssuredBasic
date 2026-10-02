
/*
 * Methods used in REST Assured
 *given() >> Pre-requisites - auth, parameters, headers, cookies...
 *when() >> Request - GET, POST, PUT, DELETE...
 *then() >> Validations - response, cookies, headers, status code...
 */

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class HTTP_Requests {

	@Test(enabled = false)
	void get_booking() {
		given()
		
		.when()
			.get("https://restful-booker.herokuapp.com/booking/222")
		.then()
			.statusCode(200)
			.body("additionalneeds", equalTo("Breakfast"))
			.log().all();
	}
	
	@Test(enabled=false)
	void post_booking() {
		// Create the nested bookingdates object
		Map<String, Object> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2027-01-01");
		bookingDates.put("checkout", "2027-01-01");

		// Create the main payload
		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "Savan");
		payload.put("lastname", "Karkar");
		payload.put("totalprice", 2232);
		payload.put("depositpaid", true);
		payload.put("bookingdates", bookingDates); // Pass the nested map here
		payload.put("additionalneeds", "Breakfast");
		
		given()
			.contentType("application/json")
			.body(payload)
		.when()
			.post("https://restful-booker.herokuapp.com/booking")
		.then()
			.statusCode(200)
			.body("booking.additionalneeds", equalTo("Breakfast"))
			.log().all();
	}
	
	@Test
	void put_booking() {
		// Create the nested bookingdates object
		Map<String, Object> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2027-01-01");
		bookingDates.put("checkout", "2027-01-01");

		// Create the main payload
		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "SavanUPD");
		payload.put("lastname", "KarkarUPD");
		payload.put("totalprice", 2232);
		payload.put("depositpaid", true);
		payload.put("bookingdates", bookingDates); // Pass the nested map here
		payload.put("additionalneeds", "Breakfast");
		
		given()
			.auth().basic("admin", "password123")
			.contentType("application/json")
			.body(payload)
		.when()
			.put("https://restful-booker.herokuapp.com/booking/862")
		.then()
			.statusCode(200)
			.body("firstname", equalTo("SavanUPD"))
			.log().all();
	}
	
	@Test
	void delete_booking() {
		given()
			.auth().basic("admin", "password123")
		.when()
			.get("https://restful-booker.herokuapp.com/booking/222")
		.then()
			.statusCode(201)
			.log().all();
	}
	
}
