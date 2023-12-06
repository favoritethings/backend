package com.ft.favoritethings.spot.repository;

import com.ft.favoritethings.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> getAllBy();

    Spot getSpotById(Long spotId);

    void deleteById(Long spotId);
}
