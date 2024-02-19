package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    public List<ShowSeatType> findAllByShow(Show show);
}
