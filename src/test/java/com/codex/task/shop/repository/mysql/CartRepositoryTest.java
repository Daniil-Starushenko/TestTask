package com.codex.task.shop.repository.mysql;

import com.codex.task.shop.model.entity.Cart;
import com.codex.task.shop.model.entity.Role;
import com.codex.task.shop.model.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartRepositoryTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void findByUser() {

        Cart cart = new Cart();
        User user = new User();
        user.setEmail("someEmail@gmail.com");
        user.setStatus(Role.USER);
        user.setNickname("nickname");
        user.setPassword("password");
        cart.setProducts(null);
        cart.setUser(user);

        userRepository.save(user);
        cartRepository.save(cart);

    }

    @Test
    void findByUserEmail() {
    }

    @Test
    void findAllByProductsIsContaining() {
    }
}