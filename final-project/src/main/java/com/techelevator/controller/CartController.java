package com.techelevator.controller;

import com.techelevator.dao.CartDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartDao cartDao;
    private final UserDao userDao;

    public CartController(CartDao cartDao, UserDao userDao) {
        this.cartDao = cartDao;
        this.userDao = userDao;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Cart getCart(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        String stateCode = userDao.getUserById(userId).getStateCode();
        return cartDao.getCart(userId, stateCode);
    }

    @RequestMapping(path = "/cart/items", method = RequestMethod.POST)
    public void addToCart(CartItem cartItem, Principal principal){
        try {
            if (cartItem != null) {
                cartDao.addOrUpdateCartItem(cartItem, userDao.findIdByUsername(principal.getName()));
            }
        }catch (RestClientResponseException e){
            e.getRawStatusCode();
        }
    }





}
