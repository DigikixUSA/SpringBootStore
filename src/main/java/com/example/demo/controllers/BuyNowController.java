package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

/**
 *  This is a controller for the Buy Now Button on the mainscreen page, with logic to control inventory
 *
 *
 *
 */

@Controller
public class BuyNowController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int theId, Model theModel) {

        Optional<Product> product2 = repository.findById((long)theId);
        if (product2.isPresent()) {
            Product product = product2.get();
            if (product.getInv() > 0){
                product.setInv(product.getInv() - 1);
                repository.save(product);
                //console debug statements
                System.out.println("product decremented!");
                // go to message html page
                return "confirmationbuyproduct";
            }//else inventory <= 0

        }
        //only reached if product.getInv() <= 0
        //console debug statement
        System.out.println("product purchase error");
        return "buyproducterror";

    }
}