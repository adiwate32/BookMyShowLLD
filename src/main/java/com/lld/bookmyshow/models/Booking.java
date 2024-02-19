package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(
        name = "bookings"
)
public class Booking extends BaseModel{
    @OneToMany(mappedBy = "booking")
    List<Payment> payments;
    @ManyToOne
    private User user;
    int amount;
    @OneToMany(mappedBy = "booking")
    List<ShowSeat> showSeats;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
