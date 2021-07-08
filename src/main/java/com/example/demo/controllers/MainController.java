package com.example.demo.controllers;

import com.example.demo.entities.CryptoCoin;
import com.example.demo.entities.CryptoExchange;
import com.example.demo.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/krypto")
public class MainController {

    @Autowired
    private CoinService coinService;

    @GetMapping("home")
    public String mainPage(@RequestParam(required = false, defaultValue = "") String filter,
                           @RequestParam(required = false, name = "startPrice") Integer startPrice,
                           @RequestParam(required = false, name = "endPrice") Integer endPrice,
                           @RequestParam(required = false, name = "compare") Boolean compare,
                            Model model) throws IOException {
        List<CryptoExchange> list = coinService.getAllExchanges(filter, startPrice, endPrice);
        model.addAttribute("compare",compare);
        model.addAttribute("oldFilter", filter);
        model.addAttribute("oldEndPrice", endPrice);
        model.addAttribute("oldStartPrice", startPrice);
        model.addAttribute("CryptoExchanges",list);
        return "mainPage";
    }

    @GetMapping("comp")
    public String compareCoins(@RequestParam Map<String, String> form,
                               Model model){
        List<CryptoCoin> compareList = coinService.getCompareCoins(form);
        model.addAttribute("coins", compareList);
        if(!compareList.isEmpty()){
            CryptoCoin maxCoin = coinService.findIdealParams(compareList);
            model.addAttribute("maxCoin", maxCoin);
        } else {
            return "redirect:/v1/krypto/home";
        }
        return "compare";
    }

}
