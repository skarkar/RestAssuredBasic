import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Log_Methods {
	
	@Test
	void verify_cookies() {
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
//			.log().all(); //Display entire response
			.log().body(true) //Display only body
			.log().cookies() //Display only Cookies
			.log().headers(); //Display only header
	}

}
