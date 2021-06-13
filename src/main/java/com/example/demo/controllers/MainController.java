package com.example.demo.controllers;

import com.example.demo.entities.CryptoExchange;
import com.example.demo.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/v1/krypto")
public class MainController {

    @Autowired
    private CoinService coinService;

    @GetMapping("home")
    public String homePage(Model model) throws IOException {
        List<CryptoExchange> list = coinService.getCoins();
        model.addAttribute("CryptoExchanges",list);
        return "mainPage";
    }

}
