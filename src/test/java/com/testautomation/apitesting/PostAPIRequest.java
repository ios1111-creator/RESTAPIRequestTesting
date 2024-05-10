package com.testautomation.apitesting;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class PostAPIRequest {

	@Test
	public void createBooking() {
		
		JSONObject booking = new JSONObject();
		JSONObject bookingdates = new JSONObject();
		
		booking.put("firstname", "POST Testing");
		booking.put("lastname", "post Tesing");
		booking.put( "totalprice", 123);
		booking.put("depositpaid" , true);
		booking.put("additionalneeds","API Testing post");
		booking.put("bookingdates",bookingdates);
		
		bookingdates.put( "checkin","2024-05-10");
		bookingdates.put("checkout", "2024-05-15");
		
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(booking.toString())
				.baseUri("https://restful-booker.herokuapp.com/booking")
//				.log().headers()
//				.log().body()
//				.log().all()
			.when()
				.post()
			.then()
				.assertThat()
//				.log().body()
//				.log().ifValidationFails()
				.statusCode(200)
				.body("booking.firstname", Matchers.equalTo("POST Testing"))
				.body("booking.totalprice", Matchers.equalTo(123))
				.body("booking.bookingdates.checkin", Matchers.equalTo("2024-05-10"));
	}
}
