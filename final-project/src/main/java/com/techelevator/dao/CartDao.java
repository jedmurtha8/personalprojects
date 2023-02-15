package com.techelevator.dao;

import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;

import java.security.Principal;

public interface CartDao {
    Cart getCart(int userId, String stateCode);

    void addOrUpdateCartItem(CartItem cartItem, int idByUsername);

    void deleteItemFromCart(int productId, int userId);

    void deleteCart (int userId);

}
