package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeat;
import com.lld.bookmyshow.models.ShowSeatType;
import com.lld.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository)
    {
        this.showSeatTypeRepository  = showSeatTypeRepository;
    }
    public int calculate_price(List<ShowSeat> showSeats, Show show)
    {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;
        for(ShowSeat showSeat: showSeats)
        {
           for(ShowSeatType showSeatType: showSeatTypes)
           {
               if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType()))
               {
                   amount += showSeatType.getAmount();
                   break;
               }
           }
        }
        return amount;
    }
}
