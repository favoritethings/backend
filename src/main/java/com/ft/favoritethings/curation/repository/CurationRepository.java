package com.ft.favoritethings.curation.repository;

import com.ft.favoritethings.curation.entity.Curation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurationRepository extends JpaRepository<Curation, Long> {
    List<Curation> findCurationByIsShowOrderByOrderNo(Boolean isShow);
}
