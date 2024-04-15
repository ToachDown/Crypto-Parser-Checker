package com.example.demo.controllers;

import com.example.demo.services.CryptoCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/v1/crypto")
@RequiredArgsConstructor
public class CryptoCoinController {

    private final CryptoCoinService coinService;

    @GetMapping("home")
    public String mainPage(@RequestParam(required = false, defaultValue = "") String filter,
                           @RequestParam(required = false, name = "startPrice") Integer startPrice,
                           @RequestParam(required = false, name = "endPrice") Integer endPrice,
                           @RequestParam(required = false, name = "compare") Boolean compare,
                            Model model) {
        model.addAttribute("compare",compare);
        model.addAttribute("oldFilter", filter);
        model.addAttribute("oldEndPrice", endPrice);
        model.addAttribute("oldStartPrice", startPrice);
        return "mainPage";
    }

}
