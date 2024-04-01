package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class RestAssured {
	// https://reqres.in/api/users?page=2&id=5
	@Test
	void testQueryAndPathParameters() {
		given().pathParam("mypath", "users") // path parameters
				.queryParam("page", 2) // query parameter
				.queryParam("id", 6) // query parameters

				.when().get("https://reqres.in/api/{mypath}")

				.then().statusCode(200).log().all();
	}
}
