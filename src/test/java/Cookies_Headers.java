import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class Cookies_Headers {

	//@Test(priority = 1)
	void verify_cookies() {
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC", "AaJma5sD1vVyUtNeznIMYIYkvVmWvOe5x_z6izx6Xnbx-keuWsDAZs8ICbc") // Verify exact value of cookie
			.log().all();
	}
	
	@Test(priority = 2)
	void capture_cookies() {
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of cookie AEC: " + cookie_value);
		
		//get all cookies
		Map<String, String> all_cookies = res.getCookies();
		
		for(String s : all_cookies.keySet()) {
			System.out.println("Cookie Name: " + s + "|| Cookie Value: " + res.getCookie(s));
		} 
	}
	
	@Test(priority = 3)
	void verify_headers() {
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.header("content-type", "text/html; charset=ISO-8859-1")
			.header("content-encoding", "gzip")
			.header("server", "gws")
			.log().all();
	}
	
	@Test(priority = 4)
	void capture_header() {
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		//get single header
		String content_type_header = res.getHeader("content-type");
		System.out.println("Value of content-type header: " + content_type_header);
		
		//get all cookies
		Headers all_headers = res.getHeaders();
		
		for(Header h : all_headers) {
			System.out.println("Header Name: " + h.getName() + "|| Header Value: " + h.getValue());
		} 
	}
}
