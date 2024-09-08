package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests2 {
	
	Faker random; 
	User userPayload; // import from "User" class. POJO
	
	
	public Logger logger; // for logs
	
	
	@BeforeClass // it will execute before starting the all the test methods
	public void setup() {
		
		random = new Faker();
		userPayload = new User();
		
		userPayload.setId(random.idNumber().hashCode());
		userPayload.setUsername(random.name().username());
		userPayload.setFirstName(random.name().firstName());
		userPayload.setLastName(random.name().lastName());
		userPayload.setEmail(random.internet().safeEmailAddress());
		userPayload.setPassword(random.internet().password(5, 10));
		userPayload.setPhone(random.phoneNumber().cellPhone());
		
		
		// LOGS
		logger = LogManager.getLogger(this.getClass());
		
		
		
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		
		logger.info("*******************  Creating a User  **********************");
		
		
		Response res = UserEndPoints2.createUser(userPayload);
		res.then().log().all();
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
		
		logger.info("*******************   User Created  **********************");
		
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		
		logger.info("*******************  Reading User Info **********************");
		
		
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
		logger.info("*******************   User Info is displayed  **********************");
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		
		logger.info("*******************  Updating a User  **********************");
		
		
		
		// userPayload.setUsername(random.name().username());
		userPayload.setFirstName(random.name().firstName());
		userPayload.setLastName(random.name().lastName());
		
		Response res = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().body();
		res.then().log().body().statusCode(200); // this is also a validation.
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200); // this is a testNG assertion
	
		// check whether data is updated or not
		Response resAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		//resAfterUpdate.then().log().all();
		
		AssertJUnit.assertEquals(resAfterUpdate.getStatusCode(), 200);		
		
		
		logger.info("*******************   User updated  **********************");
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("*******************  Deleting a User  **********************");
		
		
		Response res = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
		logger.info("*******************  User deleted  **********************");
		
	}

}
