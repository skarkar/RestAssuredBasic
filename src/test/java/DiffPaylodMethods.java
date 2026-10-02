import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffPaylodMethods {

	//@Test
	void post_hash_payload() {
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
		String addArr[] = {"Breackfast", "Lunch"};
		payload.put("additionalneeds", addArr);
		
		given()
			.contentType("application/json")
			.body(payload)
		.when()
			.post("http://localhost:3000/bookings")
		.then()
			.statusCode(201)
			.body("firstname", equalTo("Savan"))
			.body("lastname", equalTo("Karkar"))
			.body("totalprice", equalTo(2232))
			.body("additionalneeds[1]", equalTo("Lunch"))
			.log().all();
	}
	
	//@Test
	void post_org_json_payload() {
		// Create the nested bookingdates object
		Map<String, Object> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2027-01-01");
		bookingDates.put("checkout", "2027-01-01");

		// Create the main payload
		JSONObject payload = new JSONObject();
		payload.put("firstname", "Savan");
		payload.put("lastname", "Karkar");
		payload.put("totalprice", 2232);
		payload.put("depositpaid", true);
		payload.put("bookingdates", bookingDates); // Pass the nested map here
		String addArr[] = {"Breackfast", "Lunch"};
		payload.put("additionalneeds", addArr);
		
		given()
			.contentType("application/json")
			.body(payload.toString())
		.when()
			.post("http://localhost:3000/bookings")
		.then()
			.statusCode(201)
			.body("firstname", equalTo("Savan"))
			.body("lastname", equalTo("Karkar"))
			.body("totalprice", equalTo(2232))
			.body("additionalneeds[1]", equalTo("Lunch"))
			.log().all();
	}
	
	//@Test
	void post_pojo_payload() {
		// Create the nested bookingdates object
		
		Map<String, Object> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2027-01-01");
		bookingDates.put("checkout", "2027-01-01");

		// Create the main payload
		POJO_POST_Req payload = new POJO_POST_Req();
		
		payload.setFirstname("Savan");
		payload.setLastname("Karkar");
		payload.setTotalprice(3000);
		payload.setDepositepaid(true);
		payload.setBookingdates(bookingDates);
		String addArr[] = {"Breackfast", "Lunch"};
		payload.setAdditionalneeds(addArr);
		
		given()
			.contentType("application/json")
			.body(payload)
		.when()
			.post("http://localhost:3000/bookings")
		.then()
			.statusCode(201)
			.body("firstname", equalTo(payload.getFirstname()))
			.body("lastname", equalTo(payload.getLastname()))
			.body("totalprice", equalTo(payload.getTotalprice()))
			.body("additionalneeds[1]", equalTo(addArr[1]))
			.log().all();
	}

	@Test
	void external_json_payload() throws FileNotFoundException {
		File f = new File(".\\payload.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject payload = new JSONObject(jt);
		
			
		given()
			.contentType("application/json")
			.body(payload.toString())
		.when()
			.post("http://localhost:3000/bookings")
		.then()
			.statusCode(201)
			.body("firstname", equalTo("Savan"))
			.body("lastname", equalTo("Karkar"))
			.body("totalprice", equalTo(2232))
			.body("additionalneeds[1]", equalTo("Lunch"))
			.log().all();
	}
}
