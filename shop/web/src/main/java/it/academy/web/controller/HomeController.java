package it.academy.web.controller;

import it.academy.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class.getName());

    @Autowired
    PromoService promoService;

    @GetMapping("/")
    public String home(
            Model model
    ) {
        model.addAttribute("promoList", promoService.findAllPromo());
        return "index";
    }

}


