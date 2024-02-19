package com.lld.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    private long userId;
    private long showId;
    private List<Long> showSeatIds;
}
