package com.ft.favoritethings.tag.repository;

import com.ft.favoritethings.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
