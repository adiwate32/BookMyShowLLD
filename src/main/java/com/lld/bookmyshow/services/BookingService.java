package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exceptions.ShowNotFoundException;
import com.lld.bookmyshow.exceptions.UserNotFoundException;
import com.lld.bookmyshow.models.Booking;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeat;
import com.lld.bookmyshow.models.ShowSeatStatus;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.repositories.BookingRepository;
import com.lld.bookmyshow.repositories.ShowRepository;
import com.lld.bookmyshow.repositories.ShowSeatRepository;
import com.lld.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;

    BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, PriceCalculatorService priceCalculatorService)
    {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookSeats(long userId, long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty())
        {
            throw new UserNotFoundException("User Not found in DB");
        }
        User user = optionalUser.get();
        Optional<Show> optionalShow = showRepository.findById(showId);

        if(optionalShow.isEmpty())
        {
            throw new ShowNotFoundException("Show not found in DB");
        }
        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat: showSeats)
        {
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED))
            {
                throw new ShowNotFoundException("Show seat not found");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        for(ShowSeat showSeat: showSeats)
        {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.calculate_price(savedShowSeats, show));

        return bookingRepository.save(booking);
    }
}
