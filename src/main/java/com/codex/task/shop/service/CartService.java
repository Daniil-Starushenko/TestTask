package com.codex.task.shop.service;

import java.security.Principal;

public interface CartService {

    void productToCart(Integer productId, Principal principal);

}
