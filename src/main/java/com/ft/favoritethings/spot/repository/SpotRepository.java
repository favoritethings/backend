package com.ft.favoritethings.spot.repository;

import com.ft.favoritethings.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    @Override
    Optional<Spot> findById(Long aLong);
}
