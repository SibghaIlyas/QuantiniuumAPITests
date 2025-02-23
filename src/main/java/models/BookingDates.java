package models;

public class BookingDates {
    private String checkin;
    private String checkout;

    private BookingDates(BookingDatesBuilder builder) {
        this.checkin = builder.checkin;
        this.checkout = builder.checkout;
    }

    public static class BookingDatesBuilder {
        private String checkin;
        private String checkout;

        public BookingDatesBuilder withCheckin(String checkin) {
            this.checkin = checkin;
            return this;
        }

        public BookingDatesBuilder withCheckout(String checkout) {
            this.checkout = checkout;
            return this;
        }

        public BookingDates build() {
            return new BookingDates(this);
        }
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}
