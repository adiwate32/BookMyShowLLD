package com.lld.bookmyshow.controllers;

import com.lld.bookmyshow.dtos.BookMovieRequestDto;
import com.lld.bookmyshow.dtos.BookMovieResponseDto;
import com.lld.bookmyshow.dtos.ResponseStatus;
import com.lld.bookmyshow.exceptions.ShowNotFoundException;
import com.lld.bookmyshow.exceptions.UserNotFoundException;
import com.lld.bookmyshow.models.Booking;
import com.lld.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final BookingService bookingService;

    BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto) throws UserNotFoundException, ShowNotFoundException {
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookSeats(requestDto.getUserId(), requestDto.getShowId(), requestDto.getShowSeatIds());
            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
