package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(
        name = "show_seat_types"
)
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private int amount;
}
