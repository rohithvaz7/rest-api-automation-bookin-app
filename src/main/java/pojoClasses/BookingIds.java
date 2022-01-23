package pojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingIds {

    @JsonProperty("bookingid")
    private Integer bookingid;

    @JsonProperty("bookingid")
    public Integer getBookingid() {
        return bookingid;
    }

    @JsonProperty("bookingid")
    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }
}
