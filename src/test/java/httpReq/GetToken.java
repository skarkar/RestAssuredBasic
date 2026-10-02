package httpReq;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetToken {
	
	@Test
		void test_getToken(ITestContext context) {
		JSONObject payload = new JSONObject();
		
		payload.put("username", "admin");
		payload.put("password", "password123");
		
		String token = given()
			.contentType("application/json")
			.body(payload.toString())
		.when()
			.post("https://restful-booker.herokuapp.com/auth")
		.then()
			.statusCode(200)
			.body("$", hasKey("token"))
			.log().body(true)
			//.extract().path("token");
			.extract().jsonPath().getString("token");
		
		context.getSuite().setAttribute("token", token);
	}
	

}
