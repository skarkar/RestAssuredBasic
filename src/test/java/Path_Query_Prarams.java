import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Path_Query_Prarams {

	@Test
	void path_query_params() {
		given()
			.pathParam("path_name", "booking")
			.queryParam("firstname", "Savan")
			.queryParam("lastname", "Karkar")
		.when()
			.get("https://restful-booker.herokuapp.com/{path_name}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
