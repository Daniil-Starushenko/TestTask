package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.exception.entity.EntityNotFoundException;
import com.codex.task.shop.model.entity.Cart;
import com.codex.task.shop.model.entity.Product;
import com.codex.task.shop.model.entity.User;
import com.codex.task.shop.repository.mysql.CartRepository;
import com.codex.task.shop.repository.mysql.ProductRepository;
import com.codex.task.shop.repository.mysql.UserRepository;
import com.codex.task.shop.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private EmailService emailService;
    private EmailBuilder emailBuilder;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Override
    public void productToCart(Integer productId, Principal principal) {
        Product productToCart = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("product with id is not exist: " + productId));
        User user = userRepository.findUserByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("there is no user")); //optional
        Cart cart;
        if (user.getCart() == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setProducts(new HashSet<>());
        } else {
            cart = cartRepository.findByUser(user);
            if (cart.getProducts().contains(productToCart)) {
                throw new EntityIsExistException("product is exist in the cart");
            }
        }
        cart.getProducts().add(productToCart);
        cartRepository.save(cart);
    }

    @Override
    public void deleteFromCart(Integer productId, Principal principal) {
        Product productToDelete = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("product with id is not exist: " + productId));
        Cart cart = cartRepository.findByUserEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("cart is empty"));
        if (!cart.getProducts().contains(productToDelete)) {
            throw new EntityNotFoundException("cart doesnt contain");
        }
        cart.getProducts().remove(productToDelete);
        cartRepository.save(cart);
        if (cart.getProducts().isEmpty()) {
            cartRepository.delete(cart);
        }
    }

    @Override
    public void buyProductsInCart(Principal principal) {
        log.info("try to find cart and buy products");
        String userEmail = principal.getName();
        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("cart of user "
                        + userEmail + " is empty"));
        if (cart.getProducts().isEmpty()) {
            cartRepository.delete(cart);
            throw new EntityNotFoundException("there is no products to buy in cart");
        }
        sendEmailWithProductList(cart, userEmail);

        cart.setProducts(null);
        cartRepository.save(cart);
        cartRepository.delete(cart);
    }

    private void sendEmailWithProductList(Cart cart, String userEmail) {
        List<String> productList = new ArrayList<>();
        cart.getProducts().forEach(
                product -> productList.add(product.getName())
        );
        emailService.send(userEmail, emailBuilder.productsBuyingEmail(productList));
    }

}
