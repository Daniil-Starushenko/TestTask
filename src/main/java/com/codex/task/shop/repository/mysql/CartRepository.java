package com.codex.task.shop.repository.mysql;

import com.codex.task.shop.model.entity.Cart;
import com.codex.task.shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser(User user);

    Optional<Cart> findByUserEmail(User user);
}
