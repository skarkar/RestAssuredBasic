import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parse_JSON_Respo {

	//@Test
	void verify_direct_json_respo() {
		given()
		
		.when()
			.get("http://localhost:3000/bookings")
		.then()
			.body("[2].firstname", equalTo("Savan3"));
		
	}
	
	@Test
	void verify_detailed_json_respo() {
		Response res = given().when().get("http://localhost:3000/bookings");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"),"application/json");
		Assert.assertEquals(res.jsonPath().get("[2].firstname").toString(), "Savan3");
		
		//Create JSONObject object if root of JSON Response is an Object
		//JSONObject jo = new JSONObject(res.asString());
				
		//Create JSONArray object if root of JSON Response is an Array
		JSONArray ja = new JSONArray(res.getBody().asString());
		
		boolean stat = false;
		//Gets all checkin dates
		for(int i=0; i<ja.length();i++) {
			String checkin_date = ja.getJSONObject(i).getJSONObject("bookingdates").get("checkin").toString();
			System.out.println(ja.getJSONObject(i).getJSONObject("bookingdates").get("checkin").toString());
			if(checkin_date.equals("2027-01-01")) {
				stat = true;
				break;
			}
		}
		Assert.assertTrue(stat, "Checkin Date is not found");
		
		//gets all additionalneeds values
		for(int i=0; i<ja.length();i++) {
			System.out.println(ja.getJSONObject(i).get("additionalneeds").toString());
		}
	}
	
}
