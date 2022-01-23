import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.AllureLogger;
import utility.BaseTest;

import static io.restassured.RestAssured.given;

public class DeleteBooking extends BaseTest {

	@Test(description="To delete the details of the booking IDs") 
	public void getBookingIDs(){
		
		AllureLogger.logToAllure("Starting the test to delete booking details");
		/*******************************************************
		 * Send a DELETE request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		//To get the auth token
		String newAuthToken = AuthToken.post_CreateAuth();
		AllureLogger.logToAllure("Auth token is : "+newAuthToken);
		String cookieValue = "token="+newAuthToken;
		
		//Created a new booking, this will be subsequently deleted
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Eden", "Hazard", "114", "false", "2018-01-03", "2018-01-05", "Dinner");
		String IDtoUpdate = createBooking.newID;
		AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);
		AllureLogger.logToAllure("Booking ID to be deleted is : "+IDtoUpdate);
		
		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Sending the GET request for a specific booking id and receiving the response");
		Response response = given().
				spec(requestSpec).
				header("Content-Type", "application/json").
				header("Cookie", cookieValue).
				pathParam("id", IDtoUpdate).
			when().
				delete("/booking/{id}");
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 201 as this is a Delete request");
		response.then().assertThat().statusCode(201);		

		//To log the response to report
		logResponseAsString(response);

		//Checking if delete has reflected properly after the operation
		Response responseAfterDelete = given().
				spec(requestSpec).
				pathParam("id", IDtoUpdate).
				when().
				get("/booking/{id}");

		//Response after delete
		AllureLogger.logToAllure("Get Response after Delete");
		logResponseAsString(responseAfterDelete);
		Assert.assertTrue(responseAfterDelete.getStatusCode()==404);

		
	}
}
