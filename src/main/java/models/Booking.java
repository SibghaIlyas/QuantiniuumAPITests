package models;

public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
    private int id;

    private Booking(BookingBuilder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.totalprice = builder.totalprice;
        this.depositpaid = builder.depositpaid;
        this.bookingdates = builder.bookingdates;
        this.additionalneeds = builder.additionalneeds;
        this.id = builder.id;
    }

    public static class BookingBuilder {
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private BookingDates bookingdates;
        private String additionalneeds;
        private int id;

        public BookingBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public BookingBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public BookingBuilder withTotalPrice(int totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public BookingBuilder withDepositPaid(boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public BookingBuilder withBookingDates(BookingDates bookingdates) {
            this.bookingdates = bookingdates;
            return this;
        }

        public BookingBuilder withAdditionalNeeds(String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public BookingBuilder withId(int id) {
            this.id = id;
            return this;
        }
        public Booking build() {
            return new Booking(this);
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public int getId() {
        return id;
    }
}
