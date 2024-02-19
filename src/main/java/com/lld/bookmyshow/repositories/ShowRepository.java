package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Override
    public Optional<Show> findById(Long ShowId);
}
