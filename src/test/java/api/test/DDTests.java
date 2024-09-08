package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

// we don't need to mention & import the class if dataProvider present in the same class
// dataProvider will provide the data to the method
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userid, String username, String firstname, String lastname, String email, String password, String phone) 
	
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstname);
		userPayload.setLastName(lastname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		
		Response res = UserEndPoints.createUser(userPayload);
		// res.then().log().all();
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
	}
	
	
	// @Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class)
	@Test
	public void testgetUserByName(String username) 
	
	{
		Response res = UserEndPoints.readUser(username);
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=3, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void testdeleteUserByName(String username) 
	
	{
		Response res = UserEndPoints.deleteUser(username);
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
	}
	
	
	
}
