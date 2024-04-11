package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/v1/krypto")
public class MainController {

    @GetMapping("home")
    public String mainPage(@RequestParam(required = false, defaultValue = "") String filter,
                           @RequestParam(required = false, name = "startPrice") Integer startPrice,
                           @RequestParam(required = false, name = "endPrice") Integer endPrice,
                           @RequestParam(required = false, name = "compare") Boolean compare,
                            Model model) throws IOException {
        model.addAttribute("compare",compare);
        model.addAttribute("oldFilter", filter);
        model.addAttribute("oldEndPrice", endPrice);
        model.addAttribute("oldStartPrice", startPrice);
        return "mainPage";
    }

}
