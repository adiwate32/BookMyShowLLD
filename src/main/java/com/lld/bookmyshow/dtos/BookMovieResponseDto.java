package com.lld.bookmyshow.dtos;

import com.lld.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}
