package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/**
 * given(): prerequisite - content type, set cookies, add auth, add param, set headers info etc 
 * when(): get, post, put, delete 
 * then(): validate status code, extract response, extract headers, cookies and response body.
 */

public class HttpRequests {
	int id;

	//@Test(priority = 3)
	void getUsers() {
		given().when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();
	}

	@Test(priority = 1)
	void createUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "shaan");
		data.put("job", "Aurangabad");
		data.put("jobs", null);
		data.put(null, "abc");
		data.put(null, "def");
		id = given().contentType("application/json")
				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		// .then().statusCode(201).log().all();
		System.out.println(id);
	}

	@Test(priority = 2, dependsOnMethods = "createUser")
	void updateUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "khan");
		data.put("job", "QA");
		given().contentType("application/json")
		.when()
		.put("https://reqres.in/api/users/" + id)
		.then().statusCode(200).log().all();
		// System.out.println(id);
	}

	@Test(priority = 4)
	void deleteUser() {
		given()
		.when()
		.delete("https://reqres.in/api/users/" + id)
		.then().statusCode(204).log().all();
	}
}