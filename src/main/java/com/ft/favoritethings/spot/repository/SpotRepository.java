package com.ft.favoritethings.spot.repository;

import com.ft.favoritethings.spot.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
