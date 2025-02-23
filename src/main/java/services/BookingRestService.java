package services;

import io.restassured.response.Response;
import models.Booking;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class BookingRestService extends BaseService{

    private Response response;

    public BookingRestService() {

    }

    public List<Integer> getAllBookingIds() throws URISyntaxException {
        response = defaultRequestSpecification().when().get(new URI("/booking"));
        return response.jsonPath().get("bookingid");
    }

    public List<Integer> getBookingWithFilters(Map<String, String > filters) throws URISyntaxException {
        response = defaultRequestSpecification()
                .queryParams(filters)
                .when()
                .get((new URI("/booking")));
        return response.jsonPath().get("bookingid");
    }

    public int getResponseCode() {
        return response.statusCode();
    }

    public Response getBookingById(int id) throws URISyntaxException {
        response = defaultRequestSpecification().when().get(new URI("/booking/" + id));
        return response;
    }

    public Response createBooking(Booking booking) {

        return defaultRequestSpecification()
                .body(booking)
                .when().post("/booking").then().extract().response();
    }

}
