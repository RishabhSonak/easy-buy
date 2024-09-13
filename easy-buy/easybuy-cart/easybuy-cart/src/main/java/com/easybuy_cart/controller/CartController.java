package com.easybuy_cart.controller;

import com.easybuy_cart.entity.Cart;
import com.easybuy_cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/easybuy/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/{user_id}/add/{product_id}/{product_name}/{quantity}")
    public void addItemToCart(@PathVariable(name="user_id") String userId,
                              @PathVariable(name="product_id") String productId,
                              @PathVariable(name="product_name",required = true) String productName,
                              @PathVariable(name="quantity",required = true) Integer quantity){
         cartService.addItemToCart(userId,productId,productName,quantity);
    }

    @DeleteMapping("/{user_id}/remove/{product_id}")
    public String removeItemFromCart(@PathVariable(name="user_id") String userId,
                                  @PathVariable(name="product_id") String productId,
                                  @RequestParam(name="quantity",required = false) Integer quantityToRemove) {
        System.out.println(quantityToRemove);
        if (quantityToRemove==null){
            quantityToRemove=1;
        }
        return cartService.removeItemFromCart(userId,productId,quantityToRemove);
    }

    @GetMapping("/{user_id}")
    public Cart getCurrentStatusOfCart(@PathVariable(name="user_id") String userId){
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("{user_id}/clear")
    public void clearCart(@PathVariable(name="user_id") String userId){
        cartService.clearCart(userId);
    }


}
