import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojoClasses.BookingIds;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetBookings extends BaseTest {
	
	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
		return xl.getTestdata();
	}
    
	@Test()
	public void getBookingIDs(){
		
		AllureLogger.logToAllure("Starting the test to get booking details");
		/*******************************************************
		 * Send a GET request to /booking/
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the GET request for list of booking ids and receiving the response
		AllureLogger.logToAllure("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				when().
				get("/booking/");
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
		BookingIds[] bookingIds= response.as((Type) BookingIds[].class);
		Assert.assertTrue(bookingIds.length>=10);
		
	}

	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs")
	public void getBookingIDsWithNameParams(String size,
											String firstname,
											String lastname,
											String totalprice,
											String depositpaid,
											String checkin,
											String checkout,
											String additionalneeds,
											String extra){

		AllureLogger.logToAllure("Starting the test to get booking details");

		//creating the booking to get the details
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
		String IDtoUpdate = createBooking.newID;
		AllureLogger.logToAllure("New Booking ID created is : "+IDtoUpdate);
		AllureLogger.logToAllure("Booking ID to be deleted is : "+IDtoUpdate);

		/*******************************************************
		 * Send a GET request to /booking/ with filter param
		 * and check that the response has HTTP status code 200
		 ******************************************************/

		//Sending the GET request for list of booking ids and receiving the response
		AllureLogger.logToAllure("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				queryParam("firstname",firstname).
				queryParam("lastname",lastname).
				when().
				get("/booking/");

		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);

		//To log the response to report
		logResponseAsString(response);

		BookingIds[] bookingIds= response.as((Type) BookingIds[].class);
		Assert.assertTrue(bookingIds.length>=1);
	}

	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs")
	public void getBookingIDsWithDateParams(String size,
											String firstname,
											String lastname,
											String totalprice,
											String depositpaid,
											String checkin,
											String checkout,
											String additionalneeds,
											String extra){

		AllureLogger.logToAllure("Starting the test to get booking details");
		/*******************************************************
		 * Send a GET request to /booking/ with filter param
		 * and check that the response has HTTP status code 200
		 ******************************************************/

		//Sending the GET request for list of booking ids and receiving the response
		AllureLogger.logToAllure("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				queryParam("checkin",checkin).
				queryParam("checkout",checkout).
				when().
				get("/booking/");

		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);

		//To log the response to report
		logResponseAsString(response);

		BookingIds[] bookingIds= response.as((Type) BookingIds[].class);
		Assert.assertTrue(bookingIds.length>=1);
	}
}
