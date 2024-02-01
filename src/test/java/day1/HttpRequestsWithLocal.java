package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*given() --prerequisite - content type, set cookies, add auth, add param, set headers info etc

when() -- get post put delete
then() -- validate status code, extract response, extract headers, cookies and response body.
*/

public class HttpRequestsWithLocal {
	String id;

	@Test(priority = 3)
	void getUsers() {
		given().when().get("http://localhost:3000/students")

				.then().statusCode(200).log().all();

	}

	@Test(priority = 1)
	void createUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Chhotu singh");
		data.put("locations", "MP");
		data.put("phone", "01010");
		id = given().contentType("application/json").when().post("http://localhost:3000/students").jsonPath().getString("id");

		// .then().statusCode(201).log().all();
		System.out.println(id);
	}

//	@Test(priority = 2, dependsOnMethods = "createUser")
//	void updateUser() {
//		HashMap<String, String> data = new HashMap<String, String>();
//		data.put("name", "khan");
//		data.put("job", "QA");
//		given().contentType("application/json").when().put("https://reqres.in/api/users/" + id).then().statusCode(200);
//		// System.out.println(id);
//	}
//
////	// @Test(priority = 3)
////	void getUser() {
////		given().when().get("https://reqres.in/api/users/" + id)
////				.then().statusCode(200).log().all();
////	}
}