package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created for perform Create, Read, Update and Delete request to the User API.

public class UserEndPoints {
// Type of method is "Response" bcz we are returing a response type
	
	public static Response createUser(User payload) // import User from api.payload
		{
		
			Response res =
			given()
				.contentType(ContentType.JSON) // check Curl in swagger. HERE, you will see contenttype and accept is a part of HEADER.
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.post_url);
			
			return res;
		}
	
	
	public static Response readUser(String userName) // this username we will get from test later
		{
			
			Response res =
			given()
				.pathParam("username", userName)
			.when()
				.get(Routes.get_url);
			
			return res;
		}
	
		
	public static Response updateUser(String userName, User payload) 
		{
	
			Response res =
			given()
				.contentType(ContentType.JSON) 
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(Routes.update_url);
			
			return res;
		}	
	
	
	public static Response deleteUser(String userName) 
		{
		
			Response res =
			given()
				.pathParam("username", userName)
			.when()
				.delete(Routes.delete_url);
			
			return res;
		}
	
}



