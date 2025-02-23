package tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.BookingRestService;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetBookingTest {
    private final BookingRestService bookingRestService;
    private BookingDates bookingDates;
    private Booking booking;
    private int bookingID;
    Faker faker = new Faker();

    @BeforeMethod
    void setup() {
        bookingDates = new BookingDates.BookingDatesBuilder()
                .withCheckin("2018-01-01")
                .withCheckout("2019-01-01")
                .build();
        booking = new Booking.BookingBuilder()
                .withFirstname(faker.name().firstName())
                .withLastname(faker.name().lastName())
                .withTotalPrice(faker.number().numberBetween(100, 200))
                .withDepositPaid(true)
                .withBookingDates(bookingDates)
                .withAdditionalNeeds("Breakfast")
                .build();
        Response r = bookingRestService.createBooking(booking);
        bookingID = r.jsonPath().get("bookingid");

    }

    public GetBookingTest() {
        bookingRestService = new BookingRestService();
    }

    @Test
    public void verifyGetBookingIds() throws URISyntaxException {
        List<Integer> bookingIds = bookingRestService.getAllBookingIds();
        Assert.assertEquals(bookingRestService.getResponseCode(), 200);
        Assert.assertNotNull(bookingIds);

        for (Integer id : bookingIds) {
            Assert.assertTrue(id > 0);
        }
    }

    @Test
    public void verifyGetBookingWithFirstAndLastNameFilters() throws URISyntaxException {

        Map<String, String > filters = new HashMap<>();
        filters.put("firstname", booking.getFirstname());
        filters.put("lastname", booking.getLastname());

        assertBookingID(filters);
    }

    @Test
    public void verifyGetBookingWithFirstNameFilter() throws URISyntaxException {
        Map<String, String > filters = new HashMap<>();
        filters.put("firstname",  booking.getFirstname());
        assertBookingID(filters);
    }

    @Test
    public void verifyGetBookingWithLastNameFilter() throws URISyntaxException {
        Map<String, String > filters = new HashMap<>();
        filters.put("lastname", booking.getLastname());
        assertBookingID(filters);
    }


    @Test
    public void verifyGetBookingByValidID() throws URISyntaxException {
        Response r = bookingRestService.getBookingById(bookingID);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertEquals(r.body().path("firstname"), booking.getFirstname());
        Assert.assertEquals(r.body().path("lastname"), booking.getLastname());
        Assert.assertEquals(r.body().path("depositpaid"), booking.isDepositpaid());
        Assert.assertEquals(r.body().path("bookingdates.checkin"), booking.getBookingdates().getCheckin());
        Assert.assertEquals(r.body().path("bookingdates.checkout"), booking.getBookingdates().getCheckout());
        Assert.assertEquals(r.body().path("additionalneeds"), booking.getAdditionalneeds());
    }

    @Test
    public void verifyGetBookingByInValidID() throws URISyntaxException {
        Response r = bookingRestService.getBookingById(0000);
        Assert.assertEquals(r.statusCode(), 404);
    }

    public void assertBookingID(Map<String, String> filters) throws URISyntaxException {
        List<Integer> bookingIds = bookingRestService.getBookingWithFilters(filters);
        Assert.assertEquals(bookingRestService.getResponseCode(), 200);
        Assert.assertNotNull(bookingIds);
        Assert.assertTrue(bookingIds.get(0) > 0);
    }
}
