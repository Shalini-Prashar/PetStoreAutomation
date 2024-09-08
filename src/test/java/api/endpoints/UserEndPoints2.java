package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created for perform Create, Read, Update and Delete request to the User API.

public class UserEndPoints2 {

	
	// In java there is special class i.e. resource bundle to load the properties files and read the data from the file
	
			static ResourceBundle getURL() // user type: ResourceBundle, bcz return type is ResourceBundle
			{
				ResourceBundle routes = ResourceBundle.getBundle("routes"); // no need to specify the path bcz it directly refer to the src/test/resource folder
				return routes;
			}
	
	
	
	// Type of method is "Response" bcz we are returing a response type
	public static Response createUser(User payload) // import User from api.payload
		{
		
		String post_url = getURL().getString("post_url"); // fetch the particular URL
		
			Response res =
			given()
				.contentType(ContentType.JSON) // check Curl in swagger. HERE, you will see contenttype and accept is a part of HEADER.
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
			
			return res;
		}
	
	
	public static Response readUser(String userName) // this username we will get from test later
		{
		
		String get_url = getURL().getString("get_url");
			
			Response res =
			given()
				.pathParam("username", userName)
			.when()
				.get(get_url);
			
			return res;
		}
	
		
	public static Response updateUser(String userName, User payload) 
		{
		
		String update_url = getURL().getString("update_url");
	
			Response res =
			given()
				.contentType(ContentType.JSON) 
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(update_url);
			
			return res;
		}	
	
	
	public static Response deleteUser(String userName) 
		{
		
		String delete_url = getURL().getString("delete_url");
		
			Response res =
			given()
				.pathParam("username", userName)
			.when()
				.delete(delete_url);
			
			return res;
		}
	
}



