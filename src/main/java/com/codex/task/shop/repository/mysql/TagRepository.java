package com.codex.task.shop.repository.mysql;

import com.codex.task.shop.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    boolean existsByValue(String value);

    Optional<Tag> findByValue(String value);

}
