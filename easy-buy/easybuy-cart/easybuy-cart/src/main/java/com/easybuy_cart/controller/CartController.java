package com.easybuy_cart.controller;

import com.easybuy_cart.dto.AddItemRequest;
import com.easybuy_cart.entity.Cart;
import com.easybuy_cart.exception.CartException;
import com.easybuy_cart.service.CartService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RefreshScope
public class CartController {

    private final CartService cartService;
    private final Log logger= LogFactory.getLog(CartController.class);
    @Autowired
    CartController(CartService cartService){
        this.cartService=cartService;
    }
    @Value("${server.port}")
    private Integer port;
    @GetMapping("/getserver")
//    @PreAuthorize(value = "hasRole=ADMIN")
    public Integer getserverport(){
        return this.port;
    }

    // complete:
    // here first we validate userId or username through userdetails service
    // then we validate product id then we add item to cart
    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addItemToCart(@PathVariable(value = "userId") String userId,
                                           @RequestBody @Valid AddItemRequest addItemRequest) {
        logger.info("add to cart request for username: "+userId);
        try {
            cartService.addItemToCart(userId, addItemRequest);
            logger.info("successfully added to cart");
            return ResponseEntity.status(HttpStatus.CREATED).body("Item added to cart successfully.");
        } catch (CartException e) {
            logger.info("not able to add to cart");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // complete:
    @DeleteMapping("/{user_id}/remove/{product_id}")
    public String removeItemFromCart(@PathVariable(name="user_id") String userId,
                                     @PathVariable(name="product_id") String productId,
                                     @RequestParam(name="quantity",required = false) Integer quantityToRemove) {
        if (quantityToRemove==null) {
            quantityToRemove=1;
        }
        return cartService.removeItemFromCart(userId,productId,quantityToRemove);
    }

    @GetMapping("/{user_id}")
    public Cart getCurrentStatusOfCart(@PathVariable(name="user_id") String userId){
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{user_id}/clear")
    public void clearCart(@PathVariable(name="user_id") String userId){
        cartService.clearCart(userId);
    }


}
